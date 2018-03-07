package com.snhu.attendu.attendu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by tyowe on 2/23/2018.
 */

public class Absence extends AppCompatActivity{

    private TextView mCourseLabel;
    private TextView mDropdown;
    private TextView mDateSelect;
    private Button mSubmitButton;
    private View mAbsenceFormView;

    AlertDialog.Builder excuseBuilder;
    AlertDialog.Builder dateBuilder;

    private boolean isExcuseSelected;
    private boolean isDateSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);

        Intent prevIntent = getIntent(); // gets the previously created intent
        String courseName = prevIntent.getStringExtra("courseName");

        isDateSelected = false;
        isExcuseSelected = false;

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

        setupDateSelection();
        setupExcuseSelection();

        mDateSelect = (TextView) findViewById(R.id.date_select);
        mDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateBuilder.show();
               }
        });

        mDropdown = (TextView) findViewById(R.id.reason_dropdown);
        mDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excuseBuilder.show();
            }
        });
    }

    void setupExcuseSelection()
    {
        excuseBuilder = new AlertDialog.Builder(this);
        excuseBuilder.setIcon(R.drawable.ic_priority);
        excuseBuilder.setTitle(R.string.no_selection_excuse_type);
        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item);

        arrayAdapter1.add(getString(R.string.unexcused));
        arrayAdapter1.add(getString(R.string.emergency));
        arrayAdapter1.add(getString(R.string.school_related));

        excuseBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        excuseBuilder.setAdapter(arrayAdapter1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String chosenExcuse = arrayAdapter1.getItem(which);
                isExcuseSelected = true;
                mDropdown.setText(chosenExcuse);
            }
        });
    }

    void setupDateSelection()
    {
        dateBuilder = new AlertDialog.Builder(this);
        dateBuilder.setIcon(R.drawable.ic_date_range);
        dateBuilder.setTitle(R.string.no_selection_date);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_selectable_list_item);

        //TODO load previous classes from database here
        arrayAdapter.add("Dummy Date 1"); //TODO delete me
        arrayAdapter.add("Dummy Date 2"); //And me

        dateBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dateBuilder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String chosenDate = arrayAdapter.getItem(which);
                isDateSelected = true;
                showSelectedDate(chosenDate);
            }
        });
    }

    void showSelectedDate(String chosenDate)
    {
        //String formattedDate;
      //  SimpleDateFormat sdf = new SimpleDateFormat("E, MMM d, yyyy");
       // formattedDate = sdf.format(newDate.getTime());
        mDateSelect.setText(chosenDate);
    }

    void reportAbsence()
    {
        boolean cancel = false;
        View focusView = null;


        mDateSelect.setError(null);
        mDropdown.setError(null);

        if(isExcuseSelected == false)
        {
            mDropdown.setError(getString(R.string.no_selection_excuse_type));
            focusView = mDropdown;
            cancel = true;
        }

        if(isDateSelected == false)
        {
            mDateSelect.setError(getString(R.string.no_selection_date));
            focusView = mDateSelect;
            cancel = true;
        }

        if (cancel) {
            //Show field with error
            focusView.requestFocus();
        } else {
            //TODO Write absence to database

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle(R.string.success_absence)
                    .setIcon(R.drawable.ic_done_black)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent studentIntent = new Intent(getApplicationContext(), StudentMain.class);
                            startActivity(studentIntent);
                        }
                    });
            builder.show();
        }
    }
}

