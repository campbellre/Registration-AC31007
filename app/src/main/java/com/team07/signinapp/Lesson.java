package com.team07.signinapp;

import java.io.Serializable;

// Lesson class
public class Lesson implements Serializable {
    private static long serialVersionUID = 0L;

    public int id;
    public String name;
    public String location;
    public String time;
    public String date;

    public Lesson(int id, String name, String location, String time, String date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.time = time;
        this.date = date;
    }
}