package com.team07.signinapp;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class PinTests {
    private static int pinMinimum = 1000;
    private static int pinMaximum = 9999;

    @Test
    // Ensure that pin generator is generating pin numbers between 1000 and 9999 inclusive
    public void pinGeneratorWithinBounds() {
        int pin = Pin.createPinValue();
        assertTrue(pin >= pinMinimum && pin <= pinMaximum);
    }
}
