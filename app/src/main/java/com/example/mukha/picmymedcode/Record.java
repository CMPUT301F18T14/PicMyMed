package com.example.mukha.picmymedcode;

import android.location.Location;

import java.util.Date;

public class Record {

    private String title;
    private String comment;

    private Location geolocation;
    private PhotoList photoList;
    private BodyLocation bodyLocation;
    private final Date timeStamp;

    public Record(String title) {
        this.title = title;
        this.comment = "";  // Avoid null pointer exception
        this.geolocation = new Location("");
        this.photoList = new PhotoList();
        this.bodyLocation = new BodyLocation();
        this.timeStamp = new Date();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Location getGeolocation() {
        return geolocation;
    }

    public void setLocation(Location location) { this.geolocation = location; }

    public void setPhotoList(PhotoList photoList) { this.photoList = photoList; }

    public PhotoList getPhotoList() {
        return photoList;
    }

    public BodyLocation getBodyLocationList() {
        return this.bodyLocation;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

}

