package com.snhu.attendu.attendu;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Random;

public class SignInMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean pinDroped=false;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
       LatLng latLng = new LatLng(43.032710, -71.441566);
        Circle circle;
        FusedLocationProviderClient mFusedLocationClient;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mMap.addMarker(new MarkerOptions().position(latLng)).setTitle("Class");
        circle = mMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(20)
                .strokeColor(Color.DKGRAY)
                .strokeWidth(10)
                .fillColor(Color.argb(128, 255, 0, 0))
                .clickable(false));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16.0f));

        text= (TextView) findViewById(R.id.pinBox);
        int randomNumber=GenerateCode(); //Generates a random number using GenerateCode
    }

    public void goBack(View view)
    {
        Intent inten= new Intent(this,ProfMain.class);
        startActivity(inten);
    }
    public int GenerateCode()
    {
        int min= 10000;
        int max = 99999;


        Random rnd = new Random();
        int Code= rnd.nextInt(max-min+1)+min;
        return Code;

    }
    public void dropPin(final View view) {
        if (!pinDroped) {
            int randomNumber = GenerateCode();
            String random = Integer.toString(randomNumber);// casts it as a string for the TextView
            text.setText(random);
            LatLng snhu = new LatLng(43, -70);
            mMap.addMarker(new MarkerOptions().position(snhu).title("Marker in SNHU"));
            pinDroped=true;

            CountDownTimer timer = new CountDownTimer(1800000, 1) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    goBack(view);
                    removePin(view);
                    //Put in code to remove class pin
                }
            }.start();
        }
    }
    public void removePin( final View view)
    {
        pinDroped=false;
        mMap.clear();
    }
}
