package com.example.mukha.picmymedcode.Model;

import android.location.Location;

import java.util.Date;

public class Record {

    private String title;
    private String comment;
    private String id;

    private Geolocation geolocation;
    private PhotoList photoList;
    private BodyLocation bodyLocation;
    private final Date timeStamp;

    public Record(String title) {
        this.title = title;
        this.comment = "";  // Avoid null pointer exception

        this.geolocation = null;
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

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setLocation(double xCoordinate, double yCoordinate) {

        if (this.geolocation != null) {
            this.geolocation = new Geolocation(xCoordinate, yCoordinate);
        } else {
            this.geolocation.setxCoordinate(xCoordinate);
            this.geolocation.setyCoordinate(yCoordinate);
        }
    }

    public void setPhotoList(PhotoList photoList) { this.photoList = photoList; }

    public PhotoList getPhotoList() {
        return photoList;
    }

    public BodyLocation getBodyLocation() {
        return this.bodyLocation;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

