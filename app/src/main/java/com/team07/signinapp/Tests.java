package com.team07.signinapp;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class Tests {
    private static int pinMinimum = 1000;
    private static int pinMaximum = 9999;

    private static float pyongyangLatitude = 39.0392193f;
    private static float pyongyangLongitude = 125.76252410000006f;
    private static float catIslandLatitude = 24.213309f;
    private static float catIslandLongitude = -75.36453689999996f;
    private static float distanceBetweenPyongyangAndCatIslandMeters = 12650286f;
    private static float distanceToleranceMeters = 15f;

    @Test
    // Ensure that pin generator is generating pin numbers between 1000 and 9999 inclusive
    public void pinGeneratorWithinBounds() {
        int pin = Pin.createPinValue();
        assertTrue(pin >= pinMinimum && pin <= pinMaximum);
    }

    @Test
    // Check whether distance calculation is accurate
    public void assertCorrectDistanceBetweenPyongyangAndCatIsland(){
        float distance = GpsLocation.distanceBetween(pyongyangLatitude, pyongyangLongitude, catIslandLatitude, catIslandLongitude);
        assertTrue(distance >= (distanceBetweenPyongyangAndCatIslandMeters-distanceToleranceMeters) && distance <= (distanceBetweenPyongyangAndCatIslandMeters + distanceToleranceMeters));
    }

    @Test
    // Check whether current location within max distance check is correct
    public void assertCorrectDistanceEvaluationBetweenPyongyangAndCatIsland() {
        assertTrue(GpsLocation.assertDistance(pyongyangLatitude, pyongyangLongitude, catIslandLatitude, catIslandLongitude, distanceBetweenPyongyangAndCatIslandMeters + distanceToleranceMeters));
    }
}