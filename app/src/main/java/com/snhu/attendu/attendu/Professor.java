package com.snhu.attendu.attendu;

import java.util.List;

/**
 * Created by travis.guthrie1 on 10/20/2017.
 */

public class Professor extends BasicUser
{
    Professor(String userName,List<Course> courses,String user)
    {
        this.userName=userName;

        for(int i=0;i<courses.size();i++)
        {
            classSchedule.add(courses.get(i));
        }
        user="p";
    }

}
