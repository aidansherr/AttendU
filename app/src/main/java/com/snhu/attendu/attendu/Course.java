package com.snhu.attendu.attendu;


/**
 * Created by Travis on 10/11/2017.
 */
//This class is to represent a physical class that a student would be taking
public class Course
{
    String className="";
    boolean isActive=false; //A variable that will determine weather the Course has been actiated by the professor or not

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
}
