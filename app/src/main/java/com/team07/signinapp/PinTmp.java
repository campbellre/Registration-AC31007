package com.team07.signinapp;

import java.util.Random;

public class PinTmp {
    private static final int PIN_LENGTH = 4;

    public static int generate() {
        Random rng = new Random(System.nanoTime());
        String pin = "";
        int num;
        for(int i = 0; i < PIN_LENGTH; i++) {
            if(i == 0){
                num = rng.nextInt(9) + 1;
            } else {
                num = rng.nextInt(10);
            }
            pin = pin.concat(Integer.toString(num));
        }
        return Integer.parseInt(pin);
    }

    public boolean check() {
        // TODO: Pull pin from lesson in database?
        //if pin entered = lecturers pin
        return true;
    }
}