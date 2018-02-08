package com.snhu.attendu.attendu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class StudentMain extends AppCompatActivity {


    List<Course> courses=new ArrayList<Course>();
    Student newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Course math=new Course("Math");
        Course english=new Course("English");
        Course cs= new Course("Lab");

        courses.add(math);
        courses.add(english);
        courses.add(cs);

        courses.get(1).setCourseAvailability(true);

        newUser= new Student("Tyler",courses,"P");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        makeButtons();


    }
    public void openPinWindow(View view)
    {
        Intent inten= new Intent(this,CodeCheck.class);
        startActivity(inten);
    }

    public void makeButtons()
    {
        for(int i=0;i<courses.size();i++)
        {
            TableRow row = new TableRow(this);
            TableLayout tableLayout = (TableLayout)findViewById(R.id.tableLayout);
            Button btn= new Button(this);
            btn.setText(newUser.getClassName(courses.get(i)));
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
            if (courses.get(i).getCourseAvailibility() == true)
            {
                btn.setBackgroundColor(Color.GREEN);
            }
        }
    }



}
