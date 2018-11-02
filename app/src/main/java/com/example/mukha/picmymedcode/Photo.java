package com.example.mukha.picmymedcode;

public class Photo {
    private final String filepath;

    public Photo(String filepath) {
        this.filepath = filepath;
    }

    public String getPhotoPath() {
        return this.filepath;
    }
}
