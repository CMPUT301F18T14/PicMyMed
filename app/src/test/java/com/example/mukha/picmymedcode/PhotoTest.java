package com.example.mukha.picmymedcode;

import com.example.mukha.picmymedcode.Model.Photo;

import junit.framework.TestCase;

public class PhotoTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        Photo newPhoto = new Photo("/pathToPhoto");
        assertEquals("/pathToPhoto",newPhoto.getPhotoPath());
    }

    public void testPhoto() {
        Photo newPhoto = new Photo("/pathToPhoto");
        assertTrue("Wrong File Path",newPhoto.getPhotoPath().equals("/pathToPhoto"));

    }
}
