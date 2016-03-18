package com.team07.signinapp;

import android.util.Log;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Pin {

    private static String state;
    private static Integer pinCode;

    public static String getState()
    {
        return state;
    }

    public static Integer getPinCode()
    {
        return pinCode;
    }

    public static void setPinCode(Integer code)
    {
        pinCode = code;
    }

    public static Boolean checkPin(int pin, int lessonID) {
        try {
            JSONObject jsonRequest = new JSONObject();

            jsonRequest.put("pinNum", pin);
            jsonRequest.put("lessonID", lessonID); // or something similar

            ServerInteraction serverInteraction = new ServerInteraction();
            String JsonData = serverInteraction.postAndGetJson(jsonRequest, "pin/student");

            JSONObject jsonResponse = new JSONObject(JsonData);

            if (jsonResponse != null) {
                Log.i("TAG", jsonResponse.toString());
                Log.i("TAG", jsonResponse.getString("pinState"));
                if (!jsonResponse.getString("pinState").equals("Succeeded")) {
                    return false;
                }

                return true;
            }
        } catch(JSONException e) {
            // drop through
        }

        return null;
    }

    public static int createPinValue() {
        int code = 0;
        while(code < 1000) {
            code = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        }
        return code;
    }

    // Generates a pin-code to be associated with the lessonId provided and stores this code in
    // the database
    public static Boolean generatePin(int lessonID) {
        pinCode = createPinValue();
        try {
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("pinNum", pinCode);
            jsonRequest.put("lessonID", lessonID); // or something similar
            ServerInteraction serverInteraction = new ServerInteraction();
            String JsonData = serverInteraction.postAndGetJson(jsonRequest, "pin/staff");
            JSONObject jsonResponse = new JSONObject(JsonData);

            if (jsonResponse != null) {

                state = jsonResponse.getString("pinState");

                if(state.equals("Failure"))
                {
                    return false;
                }
                if(state.equals("PinIsSet"))
                {
                    return false;
                }
                if(state.equals("Succeeded"))
                {
                    return true;
                }

            }
        } catch (JSONException e) {
            // drop through
        }
        //TODO:Change back to return null
        return false;
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