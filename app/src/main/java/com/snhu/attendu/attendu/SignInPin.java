package com.snhu.attendu.attendu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import java.util.Random;
import android.content.Intent;
import android.os.CountDownTimer;

public class SignInPin extends AppCompatActivity
{
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_pin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text= (TextView) findViewById(R.id.pinBox);
        int randomNumber=GenerateCode(); //Generates a random number using GenerateCode



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
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
    public void dropPin(View view)
    {
        int randomNumber=GenerateCode();
        String random= Integer.toString(randomNumber);// casts it as a string for the TextView
        text.setText(random);

        CountDownTimer timer= new CountDownTimer(1000,100)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }

            @Override
            public void onFinish()
            {
                //Put in code to remove class pin
            }
        }.start();
    }


}
