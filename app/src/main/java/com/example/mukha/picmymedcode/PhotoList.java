package com.example.mukha.picmymedcode;

import java.util.ArrayList;

public class PhotoList {

    private ArrayList<Photo> photoList;

    PhotoList(){
        photoList = new ArrayList<Photo>();
    }

    public ArrayList<Photo> getPhotoList() {
        return photoList;
    }

    public Photo getPhoto(int index) {
        return this.photoList.get(index);
    }

    public void deletePhoto(int index) {
        this.photoList.remove(index);
    }

    public void addPhoto(Photo photo) {
        this.photoList.add(photo);
    }
}
