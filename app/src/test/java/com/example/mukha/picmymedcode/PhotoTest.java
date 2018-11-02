package com.example.mukha.picmymedcode;

import junit.framework.TestCase;

public class PhotoTest extends TestCase {

    public void testPhoto() {
        Photo newPhoto = new Photo("/pathToPhoto");
        assertTrue("Wrong File Path",newPhoto.getPhotoPath().equals("/pathToPhoto"));

    }
}
