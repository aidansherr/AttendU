package com.snhu.attendu.attendu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class Student_Main extends AppCompatActivity
{
    List<Course> courses= new ArrayList<Course>();
    Student newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {


        Course math=new Course("Math");
        Course english=new Course("English");
        Course cs= new Course("Junior lab");

        courses.add(math);
        courses.add(english);
        courses.add(cs);

        courses.get(1).setCourseAvailability(true);

        newUser= new Student("Tyler",courses,"P");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_studentlog);

    }



}
