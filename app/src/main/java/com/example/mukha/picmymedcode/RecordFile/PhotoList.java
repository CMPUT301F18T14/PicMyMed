package com.example.mukha.picmymedcode.RecordFile;

import com.example.mukha.picmymedcode.RecordFile.Photo;

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
        if (index < photoList.size()) {
            return this.photoList.get(index);
        }
        else {
            return null;
        }
    }

    public void deletePhoto(int index) {
        if (index < photoList.size()) {
            this.photoList.remove(index);
        }
    }

    public void addPhoto(Photo photo) {
        this.photoList.add(photo);
    }

    public int sizeOfPhotoList() {
        return this.photoList.size();
    }
}
