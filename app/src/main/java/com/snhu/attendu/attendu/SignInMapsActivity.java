package com.snhu.attendu.attendu;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class SignInMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
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
        LatLng snhu = new LatLng(43, -70);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(snhu));
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
    public void dropPin(final View view)
    {
        int randomNumber=GenerateCode();
        String random= Integer.toString(randomNumber);// casts it as a string for the TextView
        text.setText(random);
        LatLng snhu = new LatLng(43, -70);
        mMap.addMarker(new MarkerOptions().position(snhu).title("Marker in SNHU"));

        CountDownTimer timer= new CountDownTimer(1800000,1)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {
                goBack(view);
                removePin(view);
                //Put in code to remove class pin
            }
        }.start();
    }
    public void removePin( final View view)
    {
        mMap.clear();
    }
}
