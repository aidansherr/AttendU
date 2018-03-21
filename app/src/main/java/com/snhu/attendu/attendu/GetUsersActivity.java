package com.snhu.attendu.attendu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GetUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_users);


        final AttenduUsers allUsers= new AttenduUsers();

        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();


        databaseReference.child("Student").child("Student_ID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();
                for (DataSnapshot child:children)
                {
                    Student value = child.getValue(Student.class);
                    allUsers.addStudent(value);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("Professor").child("Professor_ID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children)
                {
                    Professor value = child.getValue(Professor.class);
                    allUsers.addProfessor(value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("IT_User").child("IT_User_ID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children= dataSnapshot.getChildren();
                for (DataSnapshot child:children)
                {
                    ITUser value = child.getValue(ITUser.class);
                    allUsers.addITUser(value);

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("Admin").child("Admin_ID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();
                for (DataSnapshot child:children)
                {
                    Admin value = child.getValue(Admin.class);
                    allUsers.addAdmin(value);
                }

                Intent i = new Intent(GetUsersActivity.this, LoginActivity.class);
                i.putExtra("AllUsers",allUsers);
                startActivity(i);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
