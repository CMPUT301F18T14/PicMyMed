package com.example.mukha.picmymedcode;

public class Record {

    private String title;
    private String comment;

    private Geolocation geolocation;
    private PhotoList photoList;
    private BodyLocationList bodyLocationList;

    public Record() {
        this(null, null);
    }
    public Record(String title, String comment) {
        this.title = title;
        this.comment = comment;
        this.geolocation = new Geolocation();
        this.photoList = new PhotoList();
        this.bodyLocationList = new BodyLocationList();
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

    public PhotoList getPhotoList() {
        return photoList;
    }

    public BodyLocationList getBodyLocationList() {
        return bodyLocationList;
    }
}

