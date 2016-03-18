package com.team07.signinapp;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lesson implements Serializable {
    private static long serialVersionUID = 0L;
    private SimpleDateFormat dateFormatDate;
    private SimpleDateFormat dateFormatTime;
    //TODO:Add variables for all data contained withing the JSON
    private int id;
    private String name;
    private String building;
    private Date dateTime;
    private String type;
    private String lecturerName;
    private Date startTime;
    private Date endTime;
    private String room;
    private int pinNum;

    public Lesson(int id, String name, String building, Date dateTime, String type, String lecturerName, Date startTime, Date endTime, String room) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.dateTime = dateTime;
        this.type = type;
        this.lecturerName = lecturerName;
        this.startTime = startTime;
        this.endTime= endTime;
        this.room = room;

        this.dateFormatDate = new SimpleDateFormat("dd/yyyy/MM");
        this.dateFormatTime = new SimpleDateFormat("kk:mm");
    }

    public Lesson(int id, String name, String building, Date dateTime, String type, Date startTime, Date endTime, String room, int pinNum) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.dateTime = dateTime;
        this.type = type;
        this.startTime = startTime;
        this.endTime= endTime;
        this.room = room;
        this.pinNum = pinNum;

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

    public void setBuilding(String building){
        this.building = building;
    }

    public String getBuilding(){
        return this.building;
    }

    public void setLecturerName(String lecturerName)
    {
        this.lecturerName = lecturerName;
    }

    public String getLecturerName()
    {
        return lecturerName;
    }

    public void setStartTime(Time startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setEndTime(Time endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setRoom(String room)
    {
        this.room = room;
    }

    public String getRoom()
    {
        return room;
    }

    public int getPinNum() {
        return pinNum;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}