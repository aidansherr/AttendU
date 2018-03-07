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
<<<<<<< HEAD
<<<<<<< HEAD

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
=======
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
=======
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'

import java.util.ArrayList;
import java.util.List;
import com.google.firebase.database.ValueEventListener;

public class StudentMain extends AppCompatActivity {

    private View mLayout;
    private TextView mStudentLabel;

    List<Course> courses=new ArrayList<Course>();
    Student newUser;
<<<<<<< HEAD
<<<<<<< HEAD


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
=======
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
=======
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'

        mLayout = findViewById(R.id.student_linear);
        mStudentLabel = (TextView) findViewById(R.id.student_label);

<<<<<<< HEAD
<<<<<<< HEAD
        //TODO Read courses from database here and student

        databaseReference.child("Student").child("Student_ID").child("-L6r1wPEIM_7FxA7CFDT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student value = dataSnapshot.getValue(Student.class);

                newUser=new Student(value.getUserName(),value.getClassList(),value.getUser());
                mStudentLabel.setText(newUser.getUserName());
                makeButtons(newUser);
            }
=======
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'

        mLayout = findViewById(R.id.student_linear);
        mStudentLabel = (TextView) findViewById(R.id.student_label);

        //TODO Read courses from database here and student

        Course math=new Course("Math");
        Course english=new Course("English");
        Course cs= new Course("Lab");

        courses.add(math);
        courses.add(english);
        courses.add(cs);

        courses.get(1).setCourseAvailability(true);

<<<<<<< HEAD

=======
=======
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        mLayout = findViewById(R.id.student_linear);
        mStudentLabel = (TextView) findViewById(R.id.student_label);

        //TODO Read courses from database here and student

        Course math=new Course("Math");
        Course english=new Course("English");
        Course cs= new Course("Lab");

        courses.add(math);
        courses.add(english);
        courses.add(cs);

        courses.get(1).setCourseAvailability(true);

>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
        newUser= new Student("Tyler",courses,"P");
        mStudentLabel.setText("Tom Brady");

        makeButtons();
<<<<<<< HEAD
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
=======
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
    }

    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,CodeCheck.class);
        startActivity(inten);
    }

    private void reportAbsence(int num)
<<<<<<< HEAD
<<<<<<< HEAD
    {
        //TODO add parameters to new intent, reference to class
        String courseAbName = newUser.getClassList().get(num).getClassName();

        Intent absenceIntent = new Intent(getApplicationContext(), Absence.class);
        absenceIntent.putExtra("courseName",courseAbName);
        startActivity(absenceIntent);
    }


    public void makeButtons(Student newUser)
    {
       LinearLayout mParentLayout = (LinearLayout) findViewById(R.id.student_linear);
        List<Course> courses=newUser.getClassList();
=======
    {
=======
    {
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
        //TODO add parameters to new intent, reference to class
        String courseAbName = courses.get(num).getClassName();

        Intent absenceIntent = new Intent(getApplicationContext(), Absence.class);
        absenceIntent.putExtra("courseName",courseAbName);
        startActivity(absenceIntent);
    }


    public void makeButtons()
    {
       LinearLayout mParentLayout = (LinearLayout) findViewById(R.id.student_linear);

<<<<<<< HEAD
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
=======
>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
        for(int i=0;i<courses.size();i++)
        {
            LinearLayout dualView = new LinearLayout(getApplicationContext());
            mParentLayout.addView(dualView);
            dualView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            dualView.setOrientation(LinearLayout.HORIZONTAL);

            Button btn= new Button(this);
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
<<<<<<< HEAD
<<<<<<< HEAD

            absenceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

=======

            absenceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
=======

            absenceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

>>>>>>> parent of 50dc79d... Merge remote-tracking branch 'origin/DatabaseWork'
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
