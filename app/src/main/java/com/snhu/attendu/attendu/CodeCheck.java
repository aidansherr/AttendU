package com.snhu.attendu.attendu;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CodeCheck extends AppCompatActivity {

    String userPinCode;
    EditText classCodeInput;

    Button submitButton;
    CheckBox userCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_check);

        classCodeInput = (EditText) findViewById(R.id.classCodeInput);
        submitButton = (Button) findViewById(R.id.submitButton);
        userCheck = (CheckBox) findViewById(R.id.checkBox);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPinCode = classCodeInput.getText().toString();



            }
        });

    }




}
