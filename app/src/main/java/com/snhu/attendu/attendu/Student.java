package com.snhu.attendu.attendu;

import java.util.List;

/**
 * Created by travis.guthrie1 on 10/24/2017.
 */

public class Student extends BasicUser
{
    Student()
    {}

    Student(String userName,String user,String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.user = user;
    }

   Student(String userName, List<Course> courses, String user)
    {
        this.userName=userName;

        for(int i=0;i<courses.size();i++)
        {
            classSchedule.add(courses.get(i));
        }
        user="s";
    }
}
