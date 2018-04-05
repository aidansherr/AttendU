package com.snhu.attendu.attendu;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Created by Travis Guthrie on 3/16/2018.
 */

public class AttenduUsers implements Serializable
{
    List<Professor> allProfs;
    List<Student> allStudents;
    List<Admin> allAdmins;
    List<ITUser> allIT;


    AttenduUsers()
    {
        allProfs= new ArrayList<>();
        allAdmins= new ArrayList<>();
        allIT= new ArrayList<>();
        allStudents= new ArrayList<>();


    }
    AttenduUsers(List<Professor> tp,List<Admin> ta,List<ITUser> ti,List<Student> ts)
    {
        allProfs=tp;
        allAdmins=ta;
        allIT= ti;
        allStudents= ts;

    }

    void addProfessor(Professor np)
    {
        allProfs.add(np);
    }
    void addAdmin(Admin ap)
    {
        allAdmins.add(ap);
    }
    void addStudent(Student ns)
    {
        allStudents.add(ns);
    }
    void addITUser(ITUser ni)
    {
        allIT.add(ni);
    }

    List<Professor> getAllProfs()
    {
        return allProfs;
    }
    List<Student> getAllStudents()
    {
        return allStudents;
    }
    List<Admin> getAllAdmins()
    {
        return allAdmins;
    }
    List<ITUser> getAllIT()
    {
        return allIT;
    }
//create if statements for each user.  if user != null then return false else return true
    Professor getProf(String name)
    {
        for( int i=0; i<allProfs.size();i++)
        {
            String tempEmail=allProfs.get(i).getEmail();
            if(tempEmail.equals(name))
            {
                return allProfs.get(i);
            }

        }
        return null;
    }
    Student getStudent(String name)
    {
        for( int i=0; i<allStudents.size();i++)
        {
            String tempEmail=allStudents.get(i).getEmail();
            if(tempEmail.equals(name))
            {
                return allStudents.get(i);
            }

        }

        return null;
    }
    Admin getAdmin(String name)
    {
        for( int i=0; i<allAdmins.size();i++)
        {
            String tempEmail=allAdmins.get(i).getEmail();
            if(tempEmail.equals(name))
            {
                return allAdmins.get(i);
            }

        }
        return null;
    }
    ITUser getIT(String name)
    {
        for( int i=0; i<allIT.size();i++)
        {
            String tempEmail=allIT.get(i).getEmail();
            if(tempEmail.equals(name))
            {
                return allIT.get(i);
            }

        }
        return null;
    }

    boolean checkEmail(String name)
    {
        if (getProf(name) != null)
        {
            return false;
        }
        if(getStudent(name)!= null)
        {
            return false;
        }
        if(getAdmin(name) != null)
        {
            return false;
        }
        if(getIT(name) != null)
        {
            return false;
        }
        return true;

    }


    int getTotalSize()
    {
        int sum=0;
        sum=allAdmins.size();
        sum+=allIT.size();
        sum+=allStudents.size();
        sum+=allProfs.size();
        return sum;
    }



}
