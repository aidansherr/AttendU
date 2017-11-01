package com.snhu.attendu.attendu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ITUser_Main extends AppCompatActivity
{
    List<Course> courses= new ArrayList<Course>();
    ITUser newUser;
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

        //newUser= new ITUser("Tyler",courses,"P");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_itlog);

    }



}
