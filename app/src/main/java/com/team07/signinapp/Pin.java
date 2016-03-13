package com.team07.signinapp;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Pin {
    // LessonID?
    public static Boolean checkPin(int pin, int lessonID) {
        try {
            JSONObject jsonRequest = new JSONObject();

            jsonRequest.put("pin", pin);
            jsonRequest.put("lessonID", lessonID); // or something similar

            ServerInteraction serverInteraction = new ServerInteraction();
            JSONObject jsonResponse = serverInteraction.postAndGetJson(jsonRequest, "pin/student");

            if (jsonResponse != null) {
                if (jsonResponse.getString("result").equals("fail")) {
                    return false;
                }

                return true;
            }

            return false;
        } catch(JSONException e) {
            // drop through
        }

        return null;
    }

    public static Integer generatePin(int lessonID) {
        int code = 0;
        while(code < 1000)
            code = Integer.parseInt(RandomStringUtils.randomNumeric(4));

        try {
            JSONObject jsonRequest = new JSONObject();

            jsonRequest.put("pinNum", code);
            jsonRequest.put("lessonID", lessonID); // or something similar

            ServerInteraction serverInteraction = new ServerInteraction();
            JSONObject jsonResponse = serverInteraction.postAndGetJson(jsonRequest, "pin/staff");

            if (jsonResponse != null) {
                if (jsonResponse.getString("result").equals("fail")) {
                    return null;
                }

                return code;
            }
        } catch (JSONException e) {
            // drop through
        }

        return null;
    }

    // NOTE: Should this be in here?
    public static Boolean isSignedIn(int lessonID, int userID) {
        // TODO: implement server side

        /*
        try {
            JSONObject jsonRequest = new JSONObject();

            jsonRequest.put("userID", userID);
            jsonRequest.put("lessonID", lessonID); // or something similar

            ServerInteraction serverInteraction = new ServerInteraction();
            JSONObject jsonResponse = serverInteraction.postAndGetJson(jsonRequest, "isSignedIn");

            if (jsonResponse != null) {
                if (jsonResponse.getString("result").equals("fail")) {
                    return null;
                } else if (jsonResponse.getString("result").equals("no")) {
                    return false;
                }

                return true;
            }
        } catch (JSONException e) {
            // drop through
        }

        return null;
        */

        return false;
    }
}
