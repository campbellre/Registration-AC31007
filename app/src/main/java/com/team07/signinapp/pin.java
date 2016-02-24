package com.team07.signinapp;

import java.util.Random;

public class pin {
    //private static final int PIN_LENGTH = 4;
    private Random rng = new Random();

    private String generatePin() {
        String pin = "";
        for(int i = 0; i < 4; i++) {
            int rpin = (rng.nextInt(10)) + 1;
            pin = pin.concat(String.valueOf(rpin));
        }
        return pin;
    }

    private boolean checkPin() {
        //if pin entered = lecturers pin
        return true;
    }
}
