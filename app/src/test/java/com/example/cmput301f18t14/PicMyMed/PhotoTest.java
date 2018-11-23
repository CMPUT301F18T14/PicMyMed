package com.example.cmput301f18t14.PicMyMed;

import com.example.cmput301f18t14.PicMyMed.Model.Photo;

import junit.framework.TestCase;

public class PhotoTest extends TestCase {

    public void testPhoto() {
        Photo newPhoto = new Photo("/pathToPhoto");
        assertTrue("Wrong File Path",newPhoto.getPhotoPath().equals("/pathToPhoto"));

    }
}
