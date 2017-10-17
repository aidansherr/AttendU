
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

    BasicUser user=this;
    
    BasicUser(String userName,List<Course> courses)
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
    
}
