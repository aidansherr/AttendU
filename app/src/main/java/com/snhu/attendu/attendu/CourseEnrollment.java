package com.snhu.attendu.attendu;

import java.util.ArrayList;
import java.util.List;

public class CourseEnrollment
{
    List<Student> courseStudents= new ArrayList<>();
    Professor courseProfessor;
    Course enrolledCourse;

    public CourseEnrollment()
    {

    }
    public CourseEnrollment(Professor newTeacher, Course newCourse)
    {
        courseProfessor=newTeacher;
        enrolledCourse=newCourse;
    }

    void addStudent(Student ns)
    {
        courseStudents.add(ns);
    }
    List<Student> getAllStudents()
    {
        return courseStudents;
    }
    String getProfessorName()
    {
        return courseProfessor.email;
    }
    Professor getCourseProfessor()
    {
        return courseProfessor;
    }
    String getCourseName()
    {
        return enrolledCourse.getClassName();
    }
    Course getEnrolledCourse()
    {
        return enrolledCourse;
    }

}
