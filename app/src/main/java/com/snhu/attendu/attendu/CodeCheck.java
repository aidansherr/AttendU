package com.snhu.attendu.attendu;

import android.content.Context;
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

    boolean codeCheck = false;



    Course course = new Course("Pin");


    public void checkPin()
    {


Context context = getApplicationContext();
CharSequence text = "Wrong Pin Number";
CharSequence text2 = "Successfully Checked In";
int duration = Toast.LENGTH_SHORT;
Toast toast = Toast.makeText(context, text, duration);
Toast toast2 = Toast.makeText(context,text2,duration);




        if(course.random.equals(userPinCode))
        {
            codeCheck = true;
            userCheck.setChecked(true);
            toast2.show(); //output that the user has signed in
            classCodeInput.setEnabled(false);
            submitButton.setEnabled(false);
        }
        else
        {


            //output that the pin did not match professor pin
            toast.show();
        }
    }


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
                checkPin();
                classCodeInput.setText("");



            }

        });

    }




}
