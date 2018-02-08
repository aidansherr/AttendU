package com.snhu.attendu.attendu;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Travis on 10/11/2017.
 */
//This class is to represent a physical class that a student would be taking
public class Course
{

    public
    SignInPin pin = new SignInPin();
    String random = "12345";
    //String random = Integer.toString(pin.GenerateCode());
    public Course()
    {
        addCourse();
    }
    Course(String className)
    {
        this.className=className;
    }
    String getClassName()
    {
        return className;
    }
    void setClassName(String newName)
    {
        className=newName;
    }
    void setCourseAvailability(boolean available)
    {
        courseCheckinAvailable = available;
    }
    boolean getCourseAvailibility()
    {
        return courseCheckinAvailable;
    }
    void addCourse()
    {
        FirebaseDatabase mDatabase;
        mDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=mDatabase.getReference();
        databaseReference.child("Course").push().setValue(this);

    }

    private

    String className="";
    boolean courseCheckinAvailable = false;
}