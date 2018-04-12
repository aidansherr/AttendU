package com.snhu.attendu.attendu;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Random;

//TODO move buttons so the current location layer can be clicked
public class SignInMapsActivity extends AppCompatActivity implements
        OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback  {

    private GoogleMap mMap;
    private Marker mMarker;
    private Circle circle;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final String TAG = SignInMapsActivity.class.getSimpleName();
    private static final int DEFAULT_ZOOM = 20;
    private LatLng latLng;
    private final LatLng mDefaultLocation = new LatLng(43.040692, -71.452886);
    private Location mLastKnownLocation;

    private boolean mLocationPermissionGranted;
    boolean pinDropped =false;


    TextView text;
    Course selectCourse;
    FirebaseDatabase mDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i= getIntent();
        selectCourse= (Course) i.getSerializableExtra("Course");


        mDatabase= FirebaseDatabase.getInstance();
         databaseReference=FirebaseDatabase.getInstance().getReference();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        setContentView(R.layout.activity_sign_in_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocationPermission();
        updateLocationUI();
        getDeviceLocation();
        text= (TextView) findViewById(R.id.pinBox);
    }

    public void goBack(View view) {
        Intent inten= new Intent(this,ProfMain.class);
        startActivity(inten);
    }

    public int generateCode() {
        int min= 10000;
        int max = 99999;

        Random rnd = new Random();
        return rnd.nextInt(max-min+1)+min;
    }

    public void dropPin(final View view) {
        try {
            long startTime=System.currentTimeMillis();

            databaseReference.child("Course").child("CourseID").child(selectCourse.getClassKey()).child("classStartedTime").setValue(startTime);

            if(mLocationPermissionGranted)
            {
                getDeviceLocation(); //Get most current location for pin drop
                latLng = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker arg0) {
                        circle.setVisible(false);
                    }

                    @SuppressWarnings("unchecked")
                    @Override
                    public void onMarkerDragEnd(Marker arg0) {
                        circle.setCenter(mMarker.getPosition());
                       circle.setVisible(true);
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(arg0.getPosition()));
                    }

                    @Override
                    public void onMarkerDrag(Marker arg0) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(mMarker.getPosition()));
                        Log.i("System out", "onMarkerDrag...");
                    }
                });

                mMarker = mMap.addMarker(new MarkerOptions().position(latLng).draggable(true).title("Class"));
                circle = mMap.addCircle(new CircleOptions()
                        .center(mMarker.getPosition())
                        .radius(20)
                        .strokeColor(Color.DKGRAY)
                        .strokeWidth(10)
                        .fillColor(Color.argb(128, 255, 0, 255))
                        .clickable(false));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
                //TODO Store database info for geofence
            } else
            {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.check_location_perms,Toast.LENGTH_LONG);
                toast.show();
            }
            //Generate code only once
            if (!pinDropped)
            {
                int randomNumber = generateCode();
                String random = Integer.toString(randomNumber);// casts it as a string for the TextView
                text.setText(random);
                selectCourse.setPin(random);
                databaseReference.child("Course").child("CourseID").child(selectCourse.getClassKey()).child("random").setValue(random);
                pinDropped = true;
            }

        }catch (Exception e){
            Log.e("Exception: %s", e.getMessage());
        }


            //Travis what does this do?????
//            CountDownTimer timer = new CountDownTimer(1800000, 1) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//
//                }
//
//                @Override
//                public void onFinish() {
//                    goBack(view);
//                    removePin(view);
//                    //Put in code to remove class pin
//                }
//            }.start();
    }
    public void removePin( final View view)
    {
        mMarker = null;
        circle = null;
        mMap.clear();
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        } else {
                            Log.d(TAG, getString(R.string.location_null));
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
    /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
}
