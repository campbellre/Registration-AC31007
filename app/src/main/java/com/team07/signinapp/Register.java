package com.team07.signinapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Register implements Serializable{
    private static long serialVersionUID = 1L;
    private List<String> students;

    public Register()
    {
        students = new ArrayList<>();
    }

    public List<String> getStudents()
    {
        return students;
    }

    public void fetchRegister(int LessonID) {
        //TODO: Fetch correct register from database;
        switch (LessonID) {
            case 1:
                students.add("A");
                students.add("B");
                students.add("C");
                students.add("D");
                students.add("A");
                students.add("B");
                students.add("C");
                students.add("D");
                students.add("A");
                students.add("B");
                students.add("C");
                students.add("D");
                students.add("A");
                students.add("B");
                students.add("C");
                students.add("D");
                break;
            case 2:
                students.add("Bob");
                students.add("Dave");
                students.add("Bill");
                students.add("Ted");
                break;
            default:
                students.add("ExampleData");
                break;
        }
    }
}
