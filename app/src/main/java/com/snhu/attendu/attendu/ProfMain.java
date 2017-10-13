package com.snhu.attendu.attendu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProfMain extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_main);
    }
    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,SignInPin.class);
        startActivity(inten);
    }
}
