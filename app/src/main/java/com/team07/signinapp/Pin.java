package com.team07.signinapp;

import org.apache.commons.lang3.RandomStringUtils;

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
    public boolean checkPin(int pin, int lessonID) {
        // TODO: Use database
        int correctPin = 1111;
        return pin == correctPin;
    }

    public int generatePin(int lessonID) {
        // TODO: Submit to database
        return Integer.parseInt(RandomStringUtils.randomNumeric(4));
    }

    //NOTE: Should this be in here?
    public boolean isSignedIn(int lessonID) {
        // TODO: Check against database
        // Will be signed in randomly
        return RandomStringUtils.random(1, "01").equals("1");
    }
}
