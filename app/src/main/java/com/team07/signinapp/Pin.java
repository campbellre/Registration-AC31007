package com.team07.signinapp;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by purplegoat53 on 25/02/16.
 */
public class Pin {

    static Pin instance = null;

    public Pin() {

    }

    // singleton?
    public static Pin getShared() {
        if(instance == null)
            instance = new Pin();
        return instance;
    }

    //lessonID?
    public boolean checkPin(String pin, int lessonID) {
        // TODO: Use database
        String correctPin = "1111";
        return pin.equals(correctPin);
    }

    public String generatePin() {
        // TODO: Submit to database
        return RandomStringUtils.randomNumeric(4);
    }
}
