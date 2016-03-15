package com.team07.signinapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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
            JSONObject jsonResponse = serverInteraction.postAndGetJson(jsonRequest, "lessons/student");
//            switch(userType)
//            {
//                case 0:
//                    jsonResponse = serverInteraction.postAndGetJson(jsonRequest, "lessons/student");
//                    break;
//                case 1:
//                    jsonResponse = serverInteraction.postAndGetJson(jsonRequest, "lessons/staff");
//                    break;
//            }
            if (jsonResponse != null) {
                System.out.println(jsonResponse.toString());
                return true;
            }
        }
        catch(JSONException e) {
            // drop through
        }
        return false;
    }


}
