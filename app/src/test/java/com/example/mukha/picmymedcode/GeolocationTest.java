package com.example.mukha.picmymedcode;

import com.example.mukha.picmymedcode.Model.Geolocation;

import junit.framework.TestCase;

public class GeolocationTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        Geolocation geolocation = new Geolocation(-5.3,6.7);
        assertEquals(-5.3, geolocation.getxCoordinate());
        assertEquals(6.7, geolocation.getyCoordinate());
    }

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
