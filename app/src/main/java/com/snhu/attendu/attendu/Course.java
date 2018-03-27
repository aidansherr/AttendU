package com.snhu.attendu.attendu;



import java.io.Serializable;
/**
 * Created by Travis on 10/11/2017.
 */
//This class is to represent a physical class that a student would be taking
public class Course implements Serializable
{

    public
    //SignInPin pin = new SignInPin();
    String random = "12345";
    //String random = Integer.toString(pin.GenerateCode());
    public Course()
    {

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

    private

    String className="";
    boolean courseCheckinAvailable = false;
}