package com.example.mukha.picmymedcode;

import junit.framework.TestCase;

import java.util.ArrayList;

public class PhotoListTest extends TestCase {

    public void testGetPhotoList() {
        PhotoList photoList = new PhotoList();
        //assertEquals("Did not return ArrayList<>", photoList.getPhotoList(), ArrayList<Photo>);
    }

    public void testAddPhoto() {
        PhotoList photoList = new PhotoList();
        Photo photo = new Photo("filepath");
        photoList.addPhoto(photo);
        assertEquals("Photo not added", photoList.getPhoto(0), photo);
    }

    public void testGetPhoto() {
        PhotoList photoList = new PhotoList();
        Photo photo1 = new Photo("filepath1");
        Photo photo2 = new Photo("filepath2");
        photoList.addPhoto(photo1);
        photoList.addPhoto(photo2);

        assertEquals("Wrong photo returned", photoList.getPhoto(0), photo1);
        assertEquals("Wrong photo returned", photoList.getPhoto(1), photo2);

        // try invalid index
        assertNull("Did not return null", photoList.getPhoto(2));
    }

    public void testDeletePhoto() {
        PhotoList photoList = new PhotoList();
        Photo photo = new Photo("filepath");

        // delete photo from empty list
        photoList.deletePhoto(0);

        // delete photo
        photoList.addPhoto(photo);
        assertEquals("Photo not added", photoList.getPhoto(0), photo);
        photoList.deletePhoto(0);
        assertNull("Photo not deleted", photoList.getPhoto(0));

        // delete photo from incorrect index
        photoList.addPhoto(photo);
        assertEquals("Photo not added", photoList.getPhoto(0), photo);
        photoList.deletePhoto(1);
    }

    public void testGetSize() {
        PhotoList photoList = new PhotoList();

        // check size 0
        assertEquals("Incorrect size returned", photoList.sizeOfPhotoList(), 0);

        // check size 1
        Photo photo = new Photo("filepath");
        photoList.addPhoto(photo);
        assertEquals("Incorrect size returned", photoList.sizeOfPhotoList(), 1);

        // check size 50
        for (int i = 1; i < 50; i++){
            Photo photo1 = new Photo(Integer.toString(i));
           photoList.addPhoto(photo1);
        }
        assertEquals("Incorrect size returned", photoList.sizeOfPhotoList(), 50);
    }
}
