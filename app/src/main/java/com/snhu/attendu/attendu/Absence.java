package com.snhu.attendu.attendu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by tyowe on 2/23/2018.
 */

public class Absence extends AppCompatActivity{

    private TextView mCourseLabel;
    private Spinner mDropdown;
    private TextView mDateSelect;
    private DatePickerDialog mDateDialog;
    private Calendar newDate;
    private Calendar calendar;
    private Button mSubmitButton;
    private View mAbsenceFormView;
    Attendance studentAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);

        Intent prevIntent = getIntent(); // gets the previously created intent
        String courseName = prevIntent.getStringExtra("courseName");
        studentAttendance= (Attendance) prevIntent.getSerializableExtra("Attendance");

        mAbsenceFormView = findViewById(R.id.absence_form);
        mCourseLabel = (TextView) findViewById(R.id.course_name_label);
        mCourseLabel.setText(courseName);

        mSubmitButton = (Button) findViewById(R.id.submitButton);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportAbsence();
            }
        });

        newDate = null;
        calendar = Calendar.getInstance();

        mDateSelect = (TextView) findViewById(R.id.date_select);
        mDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDateDialog.show();
            }
        });

        //TODO custom date picker for disabled dates
        mDateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDateDialog.updateDate(year, monthOfYear, dayOfMonth);
                newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                showSelectedDate();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        mDropdown = (Spinner) findViewById(R.id.reason_dropdown);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.reason_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDropdown.setAdapter(adapter);
    }


    void showSelectedDate()
    {
        String formattedDate;
        SimpleDateFormat sdf = new SimpleDateFormat("E, MMM d, yyyy");
        formattedDate = sdf.format(newDate.getTime());
        mDateSelect.setText(formattedDate);
    }

    void reportAbsence()
    {
        boolean cancel = false;
        View focusView = null;

        TextView dropdownError = (TextView) mDropdown.getSelectedView();

        mDateSelect.setError(null);
        dropdownError.setError(null);

        if(mDropdown.getSelectedItemPosition() == 0)
        {
            dropdownError.setError(getString(R.string.no_selection_excuse_type));
            focusView = dropdownError;
            cancel = true;
        }

        if(newDate == null)
        {
            mDateSelect.setError(getString(R.string.no_selection_date));
            focusView = mDateSelect;
            cancel = true;
        }

        if (cancel) {
            //Show field with error
            focusView.requestFocus();
        } else {
           //show success message
            //TODO show success message and switch back to activity


            //TODO Write absence to database
        }
    }
}

