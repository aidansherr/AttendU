package com.snhu.attendu.attendu;

/**
 * Created by travis.guthrie1 on 10/24/2017.
 */

public class Admin extends BasicUser
{

    Admin()
    {
        user="a";
    }
    Admin(String userName,String user,String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.user = user;
    }
    Admin( String newname)
    {
        newname=userName;
        user="a";
    }


}
