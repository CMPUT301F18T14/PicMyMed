package com.example.picmymedcode;

import com.example.picmymedcode.Model.BodyLocationPhoto;

import junit.framework.TestCase;

public class BodyLocationPhotoTest extends TestCase {

    public void testConstructor() {
        BodyLocationPhoto photo = new BodyLocationPhoto("test");

        assertEquals("test",photo.getPhotoPath());
    }

    public void testGetLabel() {
        BodyLocationPhoto photo = new BodyLocationPhoto("test");
        String testMessage = "test photo";
        photo.setLabel(testMessage);
        assertEquals("Label does not match", photo.getLabel(), testMessage);
    }

    public void testGetPhotoID() {
        BodyLocationPhoto photo = new BodyLocationPhoto("test");
        String ID = photo.getPhotoID();

        assertEquals(ID, photo.getPhotoID());
    }
}
