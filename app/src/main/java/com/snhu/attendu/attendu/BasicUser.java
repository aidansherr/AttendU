
package com.snhu.attendu.attendu;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by Travis on 10/11/2017.
 */

public class BasicUser
{
List<Class> classSchedule = new ArrayList<Class>();

    BasicUser()
    {
        Class math=new Class("Math");
        Class english=new Class("English");
        Class cs= new Class("Junior lab");

        addClass(math);
        addClass(english);
        addClass(cs);
    }


    List<Class> getClassList()
    {
        return classSchedule;
    }

    void addClass( Class newClass)
    {
        classSchedule.add(newClass);
    }

}
