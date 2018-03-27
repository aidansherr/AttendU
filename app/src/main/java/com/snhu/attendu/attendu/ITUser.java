package com.snhu.attendu.attendu;

/**
 * Created by travis.guthrie1 on 10/24/2017.
 */

public class ITUser extends BasicUser
{
    ITUser()
    {
        user="i";
    }

    ITUser(String userName,String user,String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.user = user;
    }
    ITUser( String name)
    {
        name=userName;
        user="i";
    }

}
