package com.snhu.attendu.attendu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentMain extends AppCompatActivity {



    Student newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        //This pulls the student object from the database and makes buttons based on the classes it possese
        databaseReference.child("Student").child("Student_ID").child("-L6r1wPEIM_7FxA7CFDT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student value = dataSnapshot.getValue(Student.class);

                newUser=new Student(value.getUserName(),value.getClassList(),value.getUser());
                makeButtons(newUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,CodeCheck.class);
        startActivity(inten);
    }

    public void makeButtons(Student newUser)
    {
        for(int i=0;i<newUser.getClassList().size();i++)
        {
            TableRow row = new TableRow(this);
            TableLayout tableLayout = (TableLayout)findViewById(R.id.tableLayout);
            Button btn= new Button(this);
            btn.setText(newUser.getClassName(newUser.getClassList().get(i)));
            btn.setHeight(150);
            btn.setWidth(300);
            btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    openPinWindow(view);
                }

            });
            tableLayout.addView(row);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,1.0f));
            row.addView(btn);
            if (newUser.getClassList().get(i).getCourseAvailibility() == true)
            {
                btn.setBackgroundColor(Color.GREEN);
            }
        }
    }



}
