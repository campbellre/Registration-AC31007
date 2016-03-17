package com.team07.signinapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lewis on 3/15/2016.
 */
public class LessonInterface {

    private ArrayList<Lesson> lessons;
    public LessonInterface()
    {
        lessons = new ArrayList<>();
    }

    public ArrayList<Lesson> getLessons()
    {
        return lessons;
    }

    public boolean fetchLessons(int id, int userType)
    {
        try{
            JSONObject jsonRequest = new JSONObject();

            jsonRequest.put("userId", id);

            ServerInteraction serverInteraction = new ServerInteraction();
            JSONArray jsonResponse = null;
            String JsonData = "";
            switch(userType)
            {
                case 0:
                    JsonData = serverInteraction.postAndGetJson(jsonRequest, "lessons/student");
                    jsonResponse = new JSONArray(JsonData);
                    break;
                case 1:
                    JsonData = serverInteraction.postAndGetJson(jsonRequest, "lessons/staff");
                    jsonResponse = new JSONArray(JsonData);
                    break;
            }
            if (jsonResponse != null) {
                System.out.println(jsonResponse.toString());
                for(int i=0; i<jsonResponse.length(); i++)
                {
                    JSONObject lesson = jsonResponse.getJSONObject(i);
                    //TODO: Set all objects contained within JSON
                    int lessonId = lesson.getInt("lessonId");
                    String name = lesson.getString("module");
                    Date dateTime = new Date();
                    String location = lesson.getString("building");
                    String type = lesson.getString("type");

                    lessons.add(new Lesson(lessonId, name, location, dateTime, type));
                }
            }
        }
        catch(JSONException e) {
            // drop through
        }
        return false;
    }


}
