package com.example.picmymedcode;

import com.example.picmymedcode.Model.Geolocation;

import junit.framework.TestCase;

public class GeolocationTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        Geolocation geolocation = new Geolocation(-5.3,6.7);
        assertEquals(-5.3, geolocation.getLatitude());
        assertEquals(6.7, geolocation.getLongitude());
    }

    public void testGetLatitude() {
        Geolocation geolocation = new Geolocation(-5.3, 6.7);
        assertEquals(geolocation.getLatitude(), -5.3);
    }
    public void testGetLongitude() {
        Geolocation geolocation = new Geolocation(-5.3, 6.7);
        assertEquals(geolocation.getLongitude(), 6.7);
    }
    public void testSetLatitude() {
        Geolocation geolocation = new Geolocation(-5.3, 6.7);
        geolocation.setLatitude(-7.5);
        assertEquals(geolocation.getLatitude(), -7.5);
    }
    public void testSetLongitude() {
        Geolocation geolocation = new Geolocation(-5.3, 6.7);
        geolocation.setLongitude(-7.5);
        assertEquals(geolocation.getLongitude(), -7.5);
    }

}

