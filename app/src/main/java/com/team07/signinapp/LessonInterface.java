package com.team07.signinapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

    public boolean fetchLessons(int id, int userType){
        try{
            JSONObject jsonRequest = new JSONObject();

            jsonRequest.put("userId", id);

            ServerInteraction serverInteraction = new ServerInteraction();
            JSONArray jsonResponse = null;
            String JsonData;
            switch(userType)
            {
                case 0:
                    JsonData = serverInteraction.postAndGetJson(jsonRequest, "lessons/student");
                    jsonResponse = new JSONArray(JsonData);
                    break;
                case 1:
                    JsonData = serverInteraction.postAndGetJson(jsonRequest, "lessons/lecturer");
                    jsonResponse = new JSONArray(JsonData);
                    break;
            }
            if (jsonResponse != null) {
                System.out.println(jsonResponse.toString());
                for (int i=0; i<jsonResponse.length(); i++) {
                    JSONObject lesson = jsonResponse.getJSONObject(i);
                    int lessonId = lesson.getInt("lessonId");
                    String name = lesson.getString("module");
                    Date dateTime = new Date();
                    String room = lesson.getString("room");
                    String building = lesson.getString("building") + " " + room;
                    String type = lesson.getString("lessonType");
                    Date startTime = new Date();
                    Date endTime = new Date();


                    if (userType == 0) {
                        String lecturerName = lesson.getString("lecturerName");
                        lessons.add(new Lesson(lessonId, name, building, dateTime, type, lecturerName, startTime, endTime, room));
                    }
                    else {
                        Integer pinNumInt = null;
                        JSONObject pinCode = lesson.getJSONObject("pinCode");
                        if(!(pinCode.isNull("pinNum"))) {
                            pinNumInt = 0;
                            String pinNumString = pinCode.getString("pinNum");
                            if (pinNumString != null) {
                                pinNumInt = Integer.parseInt(pinCode.getString("pinNum"));
                            }
                        }
                        lessons.add(new Lesson(lessonId, name, building, dateTime, type, startTime, endTime, room, pinNumInt));
                    }
                }
            }
        }
        catch(JSONException e) {
            // drop through
        }
        return false;
    }
}
