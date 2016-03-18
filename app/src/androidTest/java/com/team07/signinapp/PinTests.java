package com.team07.signinapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class PinTests extends ApplicationTestCase<Application> {
    private static int pinMinimum = 1000;
    private static int pinMaximum = 9999;

    public PinTests(){super(Application.class);}

    @Test
    // Ensure that pin generator is generating pin numbers between 1000 and 9999 inclusive
    public void testPinGeneratorWithinBounds() {
        int pin = Pin.createPinValue();
        assertTrue(pin >= pinMinimum && pin <= pinMaximum);
    }

}
