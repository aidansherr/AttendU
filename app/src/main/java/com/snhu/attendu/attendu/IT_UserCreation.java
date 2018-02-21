package com.snhu.attendu.attendu;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * Created by tyowe on 2/21/2018.
 */

public class IT_UserCreation extends AppCompatActivity {

    private Spinner mDropdown = (Spinner) findViewById(R.id.typeDropdown);

    //User types for the drop down
    String[] userTypes = new String[]{"Student", "Professor", "IT", "Admin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it_user_creation);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activity_it_user_creation, userTypes);
        mDropdown.setAdapter(adapter);
    }



}
