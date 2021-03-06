/*
 * Problem
 *
 * 1.2
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
package com.example.picmymedcode.Model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Problem class creates a problem object for the patient containing a date, title, description, id,
 * and list of records
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class Problem implements Serializable {
    private String startDate;
    private String title;
    private String description;
    //private RecordList recordList;
    private String problemID;
    private String username;
    private ArrayList<Record> recordList;
    private ArrayList<String> commentList;
    private static final Integer MAX_TITLE_LENGTH = 30;
    private static final Integer MAX_DESC_LENGTH = 300;


    /**
     * Constructor initializes problem class variables
     *
     * @param username                  String
     * @param startDate                 Date
     * @param title                     String
     * @param description               String
     * @throws IllegalArgumentException throws exception when title or description is too long
     */
    public Problem(String username, String startDate, String title, String description) {
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException(String.format("Problem should be less than %s characters",
                    String.valueOf(MAX_TITLE_LENGTH)));
        } else if (description.length()> MAX_DESC_LENGTH) {
            throw new IllegalArgumentException(String.format("Description should be less than %s characters",
                    String.valueOf(MAX_DESC_LENGTH)));
        } else
            {
                this.username = username;
                this.problemID = null;
                this.startDate = startDate;
                this.title = title;
                this.description = description;
                this.recordList = new ArrayList<Record>();
                this.commentList = new ArrayList<String> ();
            }
        }


    /**
     * Method gets the problem's start date
     *
     * @return  Date
     */

    public String getStartDate() {
        return startDate;
    }
    /**
     * Method sets the start date for a problem
     *
     * @param startDate Date
     */

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /**
     * Method gets the title of a problem
     *
     * @return  String
     */

    public String getTitle() {
        return title;
    }
    /**
     * Method sets the title of a problem
     *
     * @param title                     String
     * @throws IllegalArgumentException throws exception when problem title is too long
     */

    public void setTitle(String title) {
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException(String.format("Problem should be less than %s characters",
                    String.valueOf(MAX_TITLE_LENGTH)));
        } else {
            this.title = title;
        }
    }
    /**
     * Method gets problem description
     *
     * @return  String
     */

    public String getDescription() {
        return description;
    }
    /**
     * Method sets the problem description
     * @param description               String
     * @throws IllegalArgumentException throws exception when problem description is too long
     */

    public void setDescription(String description) {
        if (description.length()> MAX_DESC_LENGTH) {
            throw new IllegalArgumentException(String.format("Description should be less than %s characters",
                    String.valueOf(MAX_DESC_LENGTH)));
        } else {
            this.description = description;
        }
    }
    /**
     * Method gets a list of records
     *
     * @return  recordList
     */

    public ArrayList<Record> getRecordList(){
        return this.recordList;
    }

    /**
     * Method adds a record to the list of records
     *
     * @param record    Record
     */
    public void addRecord(Record record) {
        this.recordList.add(record);
    }

    public void removeRecord(Record record) {
        this.recordList.remove(record);
    }

    /**
     * Method gets the ID of the problem
     *
     * @return  String problemID
     */
    public String getProblemID() {
        return problemID;
    }

    /**
     * Method sets the ID for a problem
     *
     * @param problemID String
     */
    public void setProblemID(String problemID) {
        this.problemID = problemID;
    }

    /**
     * Method gets the Username of a User
     *
     * @return  String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method sets the username of a User
     *
     * @param username  String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method gets the commentList associated with a problem
     * @return
     */
    public ArrayList<String> getCommentList() {
        return commentList;
    }

    /**
     * Method sets the commentList associated with a problem
     * @param commentList
     */
    public void addCommentList(String commentList) {
        this.commentList.add(commentList);
    }

    /**
     * Method get all geolocations
     *
     * @return  allLatLng
     */
    public ArrayList<LatLng> getAllLatLng() {
        ArrayList<LatLng> allLatLng = new ArrayList<LatLng>();
        for (Record record: this.getRecordList()) {
            Geolocation geolocation = record.getGeolocation();
            if (geolocation != null) {
                allLatLng.add(new LatLng(geolocation.getLatitude(), geolocation.getLongitude()));
            }
        }
        return allLatLng;
    }
}
