package com.snhu.attendu.attendu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentMain extends AppCompatActivity {


    List<Course> courses=new ArrayList<Course>();
    Student newUser;
    private View mLayout;
    private TextView mStudentLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

        newUser= new Student("Tyler",courses,"P");
        //mStudentLabel.setText("Tom Brady");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);


        makeButtons();


    }
    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,CodeCheck.class);
        startActivity(inten);
    }

    private void reportAbsence(int num)
    {
        //TODO add parameters to new intent, reference to class
        String courseAbName = courses.get(num).getClassName();

        Intent absenceIntent = new Intent(getApplicationContext(), Absence.class);
        startActivity(absenceIntent);
    }


    public void makeButtons()
    {
       LinearLayout mParentLayout = (LinearLayout) findViewById(R.id.student_linear);

        for(int i=0;i<courses.size();i++)
        {
            LinearLayout dualView = new LinearLayout(getApplicationContext());
            mParentLayout.addView(dualView);
            dualView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            dualView.setOrientation(LinearLayout.HORIZONTAL);

            Button btn= new Button(this);
            final Button absenceBtn = new Button (this);

            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            absenceBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setId(i);
            absenceBtn.setId(i);
            btn.setBackgroundColor(Color.rgb(220, 220 ,220));
            btn.setText(newUser.getClassName(courses.get(i)));
            absenceBtn.setBackgroundColor(Color.BLUE); //TODO Make some sort of icon

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
                btn.setBackgroundColor(Color.rgb(50, 205, 50));
            }
        }
    }



}
