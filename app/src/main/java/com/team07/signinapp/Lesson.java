package com.team07.signinapp;

import java.io.Serializable;

public class Lesson implements Serializable {
    private static long serialVersionUID = 0L;

    public String name;
    public String location;
    public String time;

    public Lesson(String name, String location, String time) {
        this.name = name;
        this.location = location;
        this.time = time;
    }
}