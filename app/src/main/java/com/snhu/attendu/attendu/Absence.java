package com.snhu.attendu.attendu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by tyowe on 2/23/2018.
 */

public class Absence extends AppCompatActivity{

    private TextView mCourseLabel;
    private Spinner mDropdown;
    private TextView mDateSelect;
    private DatePickerDialog mDateDialog;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);

        Intent prevIntent = getIntent(); // gets the previously created intent
        String courseName = prevIntent.getStringExtra("courseName");

        mCourseLabel = (TextView) findViewById(R.id.course_name_label);
        mCourseLabel.setText(courseName);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mDateSelect = (TextView) findViewById(R.id.date_select);
        mDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate();
            }
        });

        mDateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            }
        }, mYear, mMonth, mDay);

        mDropdown = (Spinner) findViewById(R.id.reason_dropdown);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.reason_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDropdown.setAdapter(adapter);
    }




    private void chooseDate()
    {
        mDateDialog.show();



    }

}

