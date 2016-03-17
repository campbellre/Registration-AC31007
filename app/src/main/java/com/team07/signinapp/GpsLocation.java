package com.team07.signinapp;

import android.location.Location;

public class GpsLocation {
    public static boolean assertDistance(float lat1, float long1, float lat2, float long2, float maxDistanceMeters){
        return distanceBetween(lat1, long1, lat2, long2) >= maxDistanceMeters;
    }

    public static float distanceBetween(float lat1, float long1, float lat2, float long2){
        Location loc1 = getLocationObject(lat1, long1);
        Location loc2 = getLocationObject(lat2, long2);
        return loc1.distanceTo(loc2);
    }

    private static Location getLocationObject(float latitude, float longitude){
        Location loc = new Location("Location1");
        loc.setLatitude(latitude);
        loc.setLongitude(longitude);
        return loc;
    }
}