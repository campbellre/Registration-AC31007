package com.team07.signinapp;

import java.io.Serializable;

// Lesson class
public class Lesson implements Serializable {
    private static long serialVersionUID = 0L;

    public String name;
    public String location;
    public String time;
    public String date;

    public Lesson(String name, String location, String time, String date) {
        this.name = name;
        this.location = location;
        this.time = time;
        this.date = date;
    }
}