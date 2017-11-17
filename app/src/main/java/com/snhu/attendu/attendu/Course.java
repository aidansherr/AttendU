package com.snhu.attendu.attendu;


/**
 * Created by Travis on 10/11/2017.
 */
//This class is to represent a physical class that a student would be taking
public class Course {
    public Course(String className) {
        this.className = className;
    }

    String getClassName() {
        return className;
    }

    void setClassName(String newName) {
        className = newName;
    }

    void setCourseAvailability(boolean available) {
        courseCheckinAvailable = available;
    }

    boolean getCourseAvailibility() {
        return courseCheckinAvailable;
    }

    private

    String className = "";
    boolean courseCheckinAvailable = false;

public void checkPin()
    {

        SignInPin Pin = new SignInPin();
        String random = Integer.toString(Pin.GenerateCode());
        String userPinInput = "0";//change based on user input from pinbox

        boolean CodeCheck = false;
        if(random.equals(userPinInput))
        {
            CodeCheck = true;
        }
    else
        {
            //
        }
    }


}
