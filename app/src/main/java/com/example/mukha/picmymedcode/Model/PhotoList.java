package com.example.mukha.picmymedcode.Model;

import com.example.mukha.picmymedcode.View.TooManyListElementsException;

import java.util.ArrayList;

public class PhotoList {

    private ArrayList<Photo> photoList;
    private static final Integer MAX_NUMBER_OF_PHOTOS_PER_RECORD = 10;

    PhotoList(){
        photoList = new ArrayList<Photo>();
    }

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }

    public Photo getPhoto(int index) {
        if (index < photoList.size()) {
            return this.photoList.get(index);
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void deletePhoto(int index) {
        if (index < photoList.size()) {
            this.photoList.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void addPhoto(Photo photo) throws TooManyListElementsException {
        if (photoList.size() < MAX_NUMBER_OF_PHOTOS_PER_RECORD) {
            this.photoList.add(photo);
        } else {
            throw new TooManyListElementsException();
        }
    }

    public int sizeOfPhotoList() {
        return this.photoList.size();
    }
}
