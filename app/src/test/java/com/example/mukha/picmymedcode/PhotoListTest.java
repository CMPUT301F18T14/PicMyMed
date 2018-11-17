package com.example.mukha.picmymedcode;

import com.example.mukha.picmymedcode.Model.Photo;
import com.example.mukha.picmymedcode.Model.PhotoList;
import com.example.mukha.picmymedcode.View.TooManyListElementsException;

import junit.framework.TestCase;

import junit.framework.TestCase;

public class PhotoListTest extends TestCase {

    public void testConstructor() {
        //testing that the constructor initialized correctly
        PhotoList photoList = new PhotoList();
        assertTrue(photoList.getPhotoList() instanceof ArrayList);
    }

    public void testGetPhotoList() {
        PhotoList photoList = new PhotoList();
        assertEquals("Did not return ArrayList", photoList.getPhotoList().getClass().getName(),
                "java.util.ArrayList");
    }

    public void testGetPhoto() throws TooManyListElementsException {
        PhotoList photoList = new PhotoList();
        Photo photo1 = new Photo("filepath1");
        Photo photo2 = new Photo("filepath2");
        photoList.addPhoto(photo1);
        photoList.addPhoto(photo2);

        assertEquals("Wrong photo returned", photoList.getPhoto(0), photo1);
        assertEquals("Wrong photo returned", photoList.getPhoto(1), photo2);

        //test for invalid index
        try {
            photoList.getPhoto(2);
            fail("Exception wasn't thrown for the index, was supposed to be.");
        } catch (IndexOutOfBoundsException e){
            assertTrue("Expected to get here. Index out of bounds.", true);
        }
    }

    public void testDeletePhoto() throws TooManyListElementsException {
        PhotoList photoList = new PhotoList();
        Photo photo = new Photo("filepath");
        Photo photo1 = new Photo("filepath1");

        // delete photo from empty list
        try {
            photoList.deletePhoto(0);
            fail("Exception wasn't thrown for deleting from an empty list, was supposed to be.");
        } catch (IndexOutOfBoundsException e) {
            assertTrue("Expected to get here. Deleting from an empty list.", true);
        }

        // delete photo
        photoList.addPhoto(photo);
        assertEquals("Photo not added", photoList.getPhoto(0), photo);
        photoList.deletePhoto(0);

        //assertNull("Photo not deleted", photoList.getPhoto(0));

        //delete the correct photo
        photoList.addPhoto(photo);
        photoList.addPhoto(photo1);
        assertTrue(photoList.sizeOfPhotoList() == 2);
        photoList.deletePhoto(0);
        assertNotSame(photo,photoList.getPhoto(0));
        photoList.deletePhoto(0); //delete the second photo and make list empty again

        //delete photo from incorrect index
        photoList.addPhoto(photo);
        assertEquals("Photo not added", photoList.getPhoto(0), photo);
        try{
            photoList.deletePhoto(1);
            fail("Exception wasn't thrown for the index, was supposed to be.");
        }catch (IndexOutOfBoundsException e){
            assertTrue("Expected to get here. Index out of bounds.", true);
        }

    }

    public void testAddPhoto() {
        PhotoList photoList = new PhotoList();
        Photo photo = new Photo("filepath");

        //add one photo
        try {
            photoList.addPhoto(photo);
        } catch (TooManyListElementsException e) {
            fail("Exception shouldn't have been thrown.");
        }
        assertEquals("Photo not added", photoList.getPhoto(0), photo);

        try{
            for (int i = 0; i < 10; i++){
                photoList.addPhoto(photo);
            }
            fail("Exception wasn't thrown for adding too many elements to list, " +
                    "was supposed to be.");
        } catch (TooManyListElementsException e){
            assertTrue("Expected to be here. Added too many elements.",true);
        }

    }

    public void testGetSize() {
        PhotoList photoList = new PhotoList();

        // check size 0
        assertEquals("Incorrect size returned", photoList.sizeOfPhotoList(), 0);

        // check size 1
        try {
            Photo photo = new Photo("filepath");
            photoList.addPhoto(photo);
            assertEquals("Incorrect size returned", photoList.sizeOfPhotoList(), 1);
        } catch (TooManyListElementsException e) {
            fail("Shouldn't have more than 10 elements added to a list");
        }

        // check size 10
        try {
            for (int i = 1; i < 10; i++) {
                Photo photo1 = new Photo(Integer.toString(i));
                photoList.addPhoto(photo1);
            }
            assertEquals("Incorrect size returned", photoList.sizeOfPhotoList(), 10);
        } catch (TooManyListElementsException e) {
            fail("Shouldn't have more than 10 elements added to a list");
        }


    }

}
