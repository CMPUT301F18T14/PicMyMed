
package com.example.picmymedcode;

import com.example.picmymedcode.Model.BodyLocation;

import junit.framework.TestCase;

public class BodyLocationTest extends TestCase {

    public void testConstructor() {
        BodyLocation bodyLocation = new BodyLocation("photoID",
                1,2);

        assertEquals("photoID",bodyLocation.getPhotoID());
        assertEquals((float)1,bodyLocation.getxCoordinate());
        assertEquals((float)2,bodyLocation.getyCoordinate());
    }

    public void testGetPhotoID() {
        BodyLocation bodyLocation = new BodyLocation("photoID",
                1,2);

        assertEquals("photoID",bodyLocation.getPhotoID());
    }

    public void testSetPhotoID() {
        BodyLocation bodyLocation = new BodyLocation("photoID",
                1,2);
        bodyLocation.setPhotoID("newID");

        assertEquals("newID",bodyLocation.getPhotoID());
    }

    public void testGetXCoordinate() {
        BodyLocation bodyLocation = new BodyLocation("photoID",
                1,2);

        assertEquals((float)1,bodyLocation.getxCoordinate());
    }

    public void testSetXCoordinate() {
        BodyLocation bodyLocation = new BodyLocation("photoID",
                1,2);
        bodyLocation.setxCoordinate(22);

        assertEquals((float)22,bodyLocation.getxCoordinate());
    }

    public void testGetYCoordinate() {
        BodyLocation bodyLocation = new BodyLocation("photoID",
                1,2);

        assertEquals((float)2,bodyLocation.getyCoordinate());
    }

    public void testSetYCoordinate() {
        BodyLocation bodyLocation = new BodyLocation("photoID",
                1,2);
        bodyLocation.setyCoordinate(44);

        assertEquals((float)44,bodyLocation.getyCoordinate());
    }
}
