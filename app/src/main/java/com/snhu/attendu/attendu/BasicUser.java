
package com.snhu.attendu.attendu;

import android.content.Intent;
import java.util.List;
import java.util.ArrayList;


import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Travis on 10/11/2017.
 */

public class BasicUser
{
List<Course> classSchedule = new ArrayList<Course>();
    
    String userName="";

    String user="";

    BasicUser()
    {}

    BasicUser(String userName,List<Course> courses,String user)
    {
        this.userName=userName;

        for(int i=0;i<courses.size();i++)
        {
            classSchedule.add(courses.get(i));
        }
    }


    List<Course> getClassList()
    {
        return classSchedule;
    }

    void addCourse( Course newClass)
    {
        classSchedule.add(newClass);
    }

    String getUserName()
    {
        return userName;
    }
    
    void openActivity()
    {

    }
    String getClassName(Course course)
    {
        if (course != null) {
            return course.getClassName();

        }
        return "";
    }
    String getUser()
    {
        return user;
    }
    void setUserName(String name)
    {
        userName=name;
    }
    void setUserType(String type)
    {
        user=type;
    }



}
