package com.team07.signinapp;

import android.location.Location;

public class GpsLocation {
    public static float distanceBetween(float lat1, float long1, float lat2, float long2){
        Location loc1 = new Location("Location1");
        loc1.setLatitude(lat1);
        loc1.setLongitude(long1);

        Location loc2 = new Location("Location2");
        loc2.setLatitude(lat2);
        loc2.setLongitude(long2);

        return loc1.distanceTo(loc2);
    }
}