package com.snhu.attendu.attendu;

import java.util.List;

/**
 * Created by travis.guthrie1 on 10/20/2017.
 */

public class Professor extends BasicUser
{
<<<<<<< HEAD
=======
    Professor()
    {}

    Professor(String userName,String user,String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.user = user;
    }

>>>>>>> 927bb31cfb81a84bdcf79d9c60979e8d67e8c859
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
