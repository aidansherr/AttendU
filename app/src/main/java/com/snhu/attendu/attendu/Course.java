package com.snhu.attendu.attendu;



import java.io.Serializable;
import java.util.Random;

/**
 * Created by Travis on 10/11/2017.
 */
//This class is to represent a physical class that a student would be taking
public class Course implements Serializable
{

    public

    String random = "12345";
    //String random = Integer.toString(pin.GenerateCode());
    LatLong location = new LatLong();
    long classStartedTime;
    String classKey;

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
    void setClassLocation(int nlat, int nlng)
    {
        location.setLat(nlat);
        location.setLng(nlng);
    }
    LatLong getClassLocation()
    {
        return location;
    }
    long getClassStartedTime()
    {
        return classStartedTime;
    }
    void setClassStartedTime(long startTime)
    {
        classStartedTime=startTime;
    }
    String getClassKey()
    {
        return classKey;
    }
    void setClassKey(String nkey)
    {
        classKey=nkey;
    }
    String generatePin()
    {
        String pin="";
        int min= 10000;
        int max = 99999;


        Random rnd = new Random();
        int Code= rnd.nextInt(max-min+1)+min;
        pin=Integer.toString(Code);
        return pin;
    }
    void setPin(String np)
    {
        random=np;
    }




    private

    String className="";
    boolean courseCheckinAvailable = false;
}