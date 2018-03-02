package com.snhu.attendu.attendu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.prefs.AbstractPreferences;

/**
 * Created by tyowe on 2/23/2018.
 */

public class Absence extends AppCompatActivity{

    private TextView mCourseLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);

        Intent prevIntent = getIntent(); // gets the previously created intent
        String courseName = prevIntent.getStringExtra("courseName");

        mCourseLabel = (TextView) findViewById(R.id.course_name_label);
        mCourseLabel.setText(courseName);


    }



}
