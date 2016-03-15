package com.team07.signinapp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lesson implements Serializable {
    private static long serialVersionUID = 0L;
    private SimpleDateFormat dateFormatDate;
    private SimpleDateFormat dateFormatTime;

    private int id;
    private String name;
    private Date dateTime;
    private String location;
    private String type;

    public Lesson(int id, String name, String location, Date dateTime, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.dateTime = dateTime;
        this.type = type;

        this.dateFormatDate = new SimpleDateFormat("dd/yyyy/MM");
        this.dateFormatTime = new SimpleDateFormat("kk:mm");
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setDateTime(Date dateTime){
        this.dateTime = dateTime;
    }

    public String getDateString(){
        return dateFormatDate.format(dateTime);
    }

    public String getTimeString(){
        return dateFormatTime.format(dateTime);
    }

    public Date getDateTime(){
        return this.dateTime;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
        return this.location;
    }

    public void setType(String type) { this.type = type; }
    public String getType() { return this.type; }
}