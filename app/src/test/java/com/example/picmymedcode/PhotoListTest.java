package com.example.picmymedcode;

import com.example.picmymedcode.Model.Photo;

import junit.framework.TestCase;

import java.util.ArrayList;

public class PhotoListTest extends TestCase {

    public void testGetPhotoList() {
        ArrayList<Photo> photoList = new ArrayList<Photo>();
        assertEquals("Did not return ArrayList", photoList.getClass().getName(), "java.util.ArrayList");
    }

    public void testAddPhoto() {
        ArrayList<Photo> photoList = new ArrayList<Photo>();
        Photo photo = new Photo("filepath");
        photoList.add(photo);
        assertEquals("Photo not added", photoList.get(0), photo);
    }

    public void testGetPhoto() {
        ArrayList<Photo> photoList = new ArrayList<Photo>();
        Photo photo1 = new Photo("filepath1");
        Photo photo2 = new Photo("filepath2");
        photoList.add(photo1);
        photoList.add(photo2);

        assertEquals("Wrong photo returned", photoList.get(0), photo1);
        assertEquals("Wrong photo returned", photoList.get(1), photo2);

        // try invalid index
        assertNull("Did not return null", photoList.get(2));
    }

    public void testDeletePhoto() {
        ArrayList<Photo> photoList = new ArrayList<Photo>();
        Photo photo = new Photo("filepath");

        // delete photo from empty list
        photoList.remove(0);

        // delete photo
        photoList.add(photo);
        assertEquals("Photo not added", photoList.get(0), photo);
        photoList.remove(0);
        assertNull("Photo not deleted", photoList.get(0));

        // delete photo from incorrect index
        photoList.add(photo);
        assertEquals("Photo not added", photoList.get(0), photo);
        photoList.remove(1);
    }

    public void testGetSize() {
        ArrayList<Photo> photoList = new ArrayList<Photo>();

        // check size 0
        assertEquals("Incorrect size returned", photoList.size(), 0);

        // check size 1
        Photo photo = new Photo("filepath");
        photoList.add(photo);
        assertEquals("Incorrect size returned", photoList.size(), 1);

        // check size 50
        for (int i = 1; i < 50; i++){
            Photo photo1 = new Photo(Integer.toString(i));
           photoList.add(photo1);
        }
        assertEquals("Incorrect size returned", photoList.size(), 50);
    }
}
