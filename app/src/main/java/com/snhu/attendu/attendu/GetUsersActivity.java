package com.snhu.attendu.attendu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GetUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_users);


        final AttenduUsers allUsers= new AttenduUsers();
        final List<String> courseKeys = new ArrayList<>();
        final List<Course> courses= new ArrayList<>();


        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Course").child("CourseID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();
                for (DataSnapshot child:children)
                {
                    String skey=child.getKey();
                    Course value = child.getValue(Course.class);
                    courses.add(value);
                    courseKeys.add(skey);


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       // databaseReference.child("Student").child("Student_ID").child("-L72ExDIReWrL9wz8JVY").child("classList").child("0").child("location").child("lng").setValue(30);
        databaseReference.child("Student").child("Student_ID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();
                for (DataSnapshot child:children)
                {
                    String skey=child.getKey();
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
                    for(int i=0;i<value.classSchedule.size();i++)
                    {
                        for(int j=0;j<courses.size();j++)
                        {
                            if(value.getClassList().get(i).getClassName().equals(courses.get(j).getClassName()))
                            {
                                value.getClassList().get(i).setClassKey(courseKeys.get(j));
                            }
                        }
                    }
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

