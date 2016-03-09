package com.team07.signinapp;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Pin {
    private static Pin instance = null;

    public Pin() {
        // Empty constructor
    }

    // Singleton?
    public static Pin getShared() {
        if(instance == null) {
            instance = new Pin();
        }
        return instance;
    }

    // LessonID?
    public boolean checkPin(int pin, int lessonID) throws JSONException {
        JSONObject jsonRequest = new JSONObject();

        jsonRequest.put("pin", pin);
        jsonRequest.put("lessonID", lessonID); // or something similar

        ServerInteraction serverInteraction = new ServerInteraction();
        JSONObject jsonResponse = serverInteraction.postAndGetJson(jsonRequest, "checkPin");

        if(jsonResponse != null)
        {
            if(jsonResponse.getString("result").equals("fail")) {
                return false;
            }

            return true;
        }

        return false;
    }

    public int generatePin(int lessonID) {
        // TODO: Submit to database
        int code = 0;
        while(code < 1000){
            code = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        }
        return code;
    }

    // NOTE: Should this be in here?
    public boolean isSignedIn(int lessonID) {
        // TODO: Check against database
        // TEMP: Will be signed in randomly
        return RandomStringUtils.random(1, "01").equals("1");
    }
}
