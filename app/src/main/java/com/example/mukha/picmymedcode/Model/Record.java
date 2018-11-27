/*
 * Record
 *
 * 1.1
 *
 * Copyright (C) 2018 CMPUT301F18T14. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.mukha.picmymedcode.Model;

import android.location.Location;

import java.util.Date;

/**
 * Record class creates a record object with a title, comment, id, geolocation,
 * photolist, body location, timestamp
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class Record {

    private String title;
    private String comment;
    private String description;
    private Location geolocation;
    private PhotoList photoList;
    private BodyLocation bodyLocation;
    private final Date timeStamp;

    /**
     * Constructor initializes variables for Record
     *
     * @param title String
     */
    public Record(String title) {
        this.title = title;
        this.comment = "";  // Avoid null pointer exception
        this.description = "no description";
        this.geolocation = new Location("");
        this.photoList = new PhotoList();
        this.bodyLocation = new BodyLocation();
        this.timeStamp = new Date();

    }

    /**
     * Method gets the title of a record
     *
     * @return  String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method sets the title of a record
     *
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Method gets the comment of a record
     *
     * @return  String
     */
    public String getComment() {
        return comment;
    }

    /**
     * Method sets comment for a record
     *
     * @param comment   String
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Method gets comment description
     *
     * @return  String
     */
    public String getDescription (){
        return description;
    }

    /**
     * Method sets description for record
     *
     * @param description String
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Method gets record geolocation
     *
     * @return geolocation
     */
    public Location getGeolocation() {
        return geolocation;
    }

    /**
     * Method sets record location
     *
     * @param location Location
     */
    public void setLocation(Location location) { this.geolocation = location; }

    /**
     * Method sets photo list for the record
     *
     * @param photoList photoList
     */
    public void setPhotoList(PhotoList photoList) { this.photoList = photoList; }

    /**
     * Method gets list of photos
     *
     * @return photoList
     */
    public PhotoList getPhotoList() {
        return photoList;
    }

    /**
     * Method gets body locations
     *
     * @return BodyLocation
     */
    public BodyLocation getBodyLocation() {
        return this.bodyLocation;
    }

    /**
     * Method gets the timestamp of the record
     *
     * @return  timeStamp
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

}

