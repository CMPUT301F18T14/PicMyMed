package com.example.mukha.picmymedcode;

import android.location.Location;

import junit.framework.TestCase;

public class GeolocationTest extends TestCase {

    public void testGetXLocation() {
        Geolocation geolocation = new Geolocation(-5.3, 6.7);
        assertEquals(geolocation.getxCoordinate(), -5.3);
    }
    public void testGetYLocation() {
        Geolocation geolocation = new Geolocation(-5.3, 6.7);
        assertEquals(geolocation.getyCoordinate(), 6.7);
    }
    public void testSetXLocation() {
        Geolocation geolocation = new Geolocation(-5.3, 6.7);
        geolocation.setxCoordinate(-7.5);
        assertEquals(geolocation.getxCoordinate(), -7.5);
    }
    public void testSetYLocation() {
        Geolocation geolocation = new Geolocation(-5.3, 6.7);
        geolocation.setyCoordinate(-7.5);
        assertEquals(geolocation.getyCoordinate(), -7.5);
    }

}
