package com.snhu.attendu.attendu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ITUserMain extends AppCompatActivity {

    private Button mCreateButton;
    final ITUser newUser=new ITUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();


        databaseReference.child("IT_user").child("IT_User_ID").child("-L6xb8CWAoPQIk1mc9ee").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ITUser value = dataSnapshot.getValue(ITUser.class);
                newUser.setUserName(value.getUserName());
                newUser.setUserType(value.getUser());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setContentView(R.layout.activity_ituser_main);

        mCreateButton = (Button) findViewById(R.id.createUserButton);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserTask(v);
            }
        });
    }

    public void createUserTask(View view)
    {
        Intent userCreation = new Intent(getApplicationContext(), IT_UserCreation.class);
        startActivity(userCreation);
    }
}