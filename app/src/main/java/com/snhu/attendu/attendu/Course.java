package com.snhu.attendu.attendu;


/**
 * Created by Travis on 10/11/2017.
 */
//This class is to represent a physical class that a student would be taking
public class Course
{
    public
    Course(String className)
    {
        this.className=className;
    }
    String getClassName()
    {
        return className;
    }
    void setClassName(String newName)
    {
        className=newName;
    }
    void setCourseAvailability(boolean available)
    {
        courseCheckinAvailable = available;
    }
    boolean getCourseAvailibility()
    {
        return courseCheckinAvailable;
    }

    private

    String className="";
    boolean courseCheckinAvailable = false;



    boolean codeCheck = false;


    public void checkPin()
    {
        CodeCheck code = new CodeCheck();
        SignInPin Pin = new SignInPin();
        String random = Integer.toString(Pin.GenerateCode());

        if(random.equals(code.userPinCode))
        {
            codeCheck = true;
            code.userCheck.setChecked(true);
        }
        else
        {
            //output that the pin did not match professor pin
        }
    }
}
