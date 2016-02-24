package com.team07.signinapp;
import java.util.*;
/**
 * Created by Mantis on 24/02/2016.
 */
public class pin {

    //private static final int PIN_LENGHT = 4;
    Random rng = new Random();
    int rpin;

    private String generatePin()
    {
        String pin = "";
        for(int i = 0; i < 4; i++)
        {
            rpin = (rng.nextInt(10)) + 1;
            pin = pin.concat(String.valueOf(rpin));
        }
        return pin;
    }

    private boolean checkPin()
    {
        //if pin entered = lecturers pin
        return true;
    }
}
