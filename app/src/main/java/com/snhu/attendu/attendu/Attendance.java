package com.snhu.attendu.attendu;
import java.io.Serializable;

public class Attendance implements Serializable
{
    boolean present=false;
    String studentUserName="";
    String dateOfClass="";
    String reasonForAbcence="";

    public Attendance()
    {

    }

    public Attendance(String studentUserName, String dateOfClass)
    {
        this.studentUserName=studentUserName;
        this.dateOfClass=dateOfClass;
    }

    public void setPresent()
    {
        present=true;
    }
    public boolean getPresent()
    {
        return present;
    }
    public void setReasonForAbcence(String reason)
    {
        reasonForAbcence=reason;
    }

    public String getReasonForAbcence() {
        return reasonForAbcence;
    }

    public void setStudentUserName(String user)
    {
        studentUserName=user;
    }
    public String getStudentUserName()
    {
        return studentUserName;
    }

    public void setDateOfClass(String ndate)
    {
        dateOfClass=ndate;
    }
    public String getDateOfClass()
    {
        return dateOfClass;
    }

}
