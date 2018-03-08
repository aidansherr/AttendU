package com.snhu.attendu.attendu;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.String;

/**
 * Created by tyowe on 2/21/2018.
 */

public class IT_UserCreation extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mNameView;
    private Button mSubmit;
    private View mProgressView;
    private View mLoginFormView;
    private Spinner mDropdown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it_user_creation);

        mEmailView = (EditText) findViewById(R.id.emailText);
        mPasswordView = (EditText) findViewById(R.id.passwordText);
        mDropdown = (Spinner) findViewById(R.id.typeDropdown);
        mSubmit = (Button) findViewById(R.id.submitButton);
        mNameView= (EditText) findViewById(R.id.nameText);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDropdown.setAdapter(adapter);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        mSubmit = (Button) findViewById(R.id.submitButton);
        mSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                  createUser();
            }
        });
    }

    private void createUser(){

        boolean cancel = false;
        View focusView = null;

        TextView dropdownError = (TextView)mDropdown.getSelectedView();

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String passwordEncrypted = PasswordDigest.encryptPassword(mPasswordView.toString());
        String typeOfUser = String.valueOf(mDropdown.getSelectedItem());
        String name = mNameView.getText().toString();

        mEmailView.setError(null);
        mPasswordView.setError(null);
        dropdownError.setError(null);

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            focusView = mPasswordView;
            cancel = true;
            mPasswordView.setText("");
        } else if (TextUtils.isEmpty(password)){
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if(mDropdown.getSelectedItemPosition() == 0) {
            dropdownError.setError(getString(R.string.no_selection_user_type));
            focusView = dropdownError;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            showProgress(true);

            //TODO Write new user data to database
            //email, password encrypted, typeofUser

            FirebaseDatabase mDatabase;
            mDatabase= FirebaseDatabase.getInstance();
            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
            switch (typeOfUser)
            {
                case "Student":
                    Student newUser1= new Student(name,"s",email,passwordEncrypted);
                    databaseReference.child("Student").child("Student_ID").push().setValue(newUser1);
                    break;
                case "Professor":
                    Professor newUser2= new Professor(name,"p",email,passwordEncrypted);
                    databaseReference.child("Professor").child("Professor_ID").push().setValue(newUser2);
                    break;
                case "Admin":
                    Admin newUser3= new Admin(name,"a",email,passwordEncrypted);
                    databaseReference.child("Admin").child("Admin_ID").push().setValue(newUser3);
                    break;
                case "IT":
                    ITUser newUser4= new ITUser(name,"i",email,passwordEncrypted);
                    databaseReference.child("IT_User").child("IT_User_ID").push().setValue(newUser4);
                    break;
                default:
                    break;
            }
        }
        AlertDialog.Builder builder= new AlertDialog.Builder(IT_UserCreation.this);
        builder.setTitle("Succsesful");
        builder.setMessage("User created");
        builder.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Intent i= new Intent(IT_UserCreation.this,ITUserMain.class);
                startActivity(i);
            }
        }).create().show();



    }

    private boolean isEmailValid(String email) {

        //TODO check here that a duplicate user is not being created

        //Username must be snhu email account and have a name before it
        return (email.endsWith("@snhu.edu")) && (email.length() > 9);
    }

    private boolean isPasswordValid(String password) {
        //Password must contain 8 characters, contain a capitol letter, and a number
        if (password.length() < 8)
        {
            mPasswordView.setError(getString(R.string.error_password_short));
            return false;
        }
        else if(password.equals(password.toLowerCase()))
        {
            mPasswordView.setError(getString(R.string.error_passsword_uppercase));
            return false;
        }
        else if (!password.matches(".*\\d+.*"))
        {
            mPasswordView.setError(getString(R.string.error_password_number));
            return false;
        }

        return true;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
