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

>>>>>>> 2dea3b17863775da2f07906ee4367ddb48b68e8a
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
