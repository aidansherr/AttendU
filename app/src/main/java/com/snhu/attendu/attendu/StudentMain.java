package com.snhu.attendu.attendu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import com.google.firebase.database.ValueEventListener;

public class StudentMain extends AppCompatActivity {

    private View mLayout;
    private TextView mStudentLabel;


    Student newUser;
    Attendance studentAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();




        super.onCreate(savedInstanceState);

        Intent i= getIntent();
        newUser= (Student) i.getSerializableExtra("Student");
        final List<Course> allCourses= new ArrayList<Course>();
        //studentAttendance= new Attendance(newUser.getEmail(),"4/5/2018");


        databaseReference.child("Course").child("CourseID").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children)
                {
                    Course value=child.getValue(Course.class);
                    allCourses.add(value);
                }

                for(int i=0;i<allCourses.size();i++)
                {
                    long temp2=allCourses.get(i).getClassStartedTime();
                    for(int j=0;j<newUser.getClassList().size();j++)
                    {
                        if(newUser.getClassList().get(j).getClassName().equals(allCourses.get(i).getClassName()))
                        {

                            newUser.getClassList().get(j).setClassStartedTime(temp2);

                        }
                    }

                }

                }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {

                    }
        });


                setContentView(R.layout.activity_student_main);
        //
                mLayout = findViewById(R.id.student_linear);
                mStudentLabel = (TextView) findViewById(R.id.student_label);
        makeButtons(newUser);

        //
        //
        //
        //
        //


    }

    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,CodeCheck.class);
        Course temp=newUser.getClassList().get(view.getId());
        long t=temp.getClassStartedTime();
        inten.putExtra("Class",temp);
        inten.putExtra("Student",newUser);
        inten.putExtra("Attendance",studentAttendance);
        startActivity(inten);
    }

    private void reportAbsence(int num)
    {
        //TODO add parameters to new intent, reference to class
        String courseAbName = newUser.getClassList().get(num).getClassName();

        Intent absenceIntent = new Intent(getApplicationContext(), Absence.class);
        absenceIntent.putExtra("courseName",courseAbName);
        absenceIntent.putExtra("Attendance",studentAttendance);
        startActivity(absenceIntent);
    }


    public void makeButtons(Student newUser)
    {
       LinearLayout mParentLayout = (LinearLayout) findViewById(R.id.student_linear);
        List<Course> courses=newUser.getClassList();
        for(int i=0;i<courses.size();i++)
        {
            LinearLayout dualView = new LinearLayout(getApplicationContext());
            mParentLayout.addView(dualView);
            dualView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            dualView.setOrientation(LinearLayout.HORIZONTAL);

            final Button btn= new Button(this);
            final Button absenceBtn = new Button (this);
            LinearLayout.LayoutParams buttonParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
            LinearLayout.LayoutParams aButtonParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
            buttonParams.weight = 1;
            aButtonParams.weight = 3;
            btn.setLayoutParams(buttonParams);
            absenceBtn.setLayoutParams(aButtonParams);
            buttonParams.setMargins(0,60,0,0);
            aButtonParams.setMargins(0,60,0,0);
            btn.setId(i);
            absenceBtn.setId(i);
            btn.setText(newUser.getClassName(courses.get(i)));

            absenceBtn.setText(R.string.report_absence);
            absenceBtn.setBackgroundColor(Color.LTGRAY);

            dualView.addView(btn);
            dualView.addView(absenceBtn);

            btn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    openPinWindow(view);
                }
            });

            absenceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    reportAbsence(absenceBtn.getId());
                }
            });

            if (courses.get(i).getCourseAvailibility() == true)
            {
                btn.setBackgroundColor(Color.rgb(50, 205, 50)); //GREEN
            }else
            {
                btn.setBackgroundColor(Color.rgb(220, 220 ,220)); //GRAY
            }
        }
    }
}
