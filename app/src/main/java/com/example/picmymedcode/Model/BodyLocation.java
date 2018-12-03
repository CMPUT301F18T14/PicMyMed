/*
 * BodyLocation
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

package com.example.picmymedcode.Model;


import java.io.Serializable;
import java.util.HashMap;

/**
 * BodyLocation stores a body location photo and a mark on that photo indicated by the user
 * Note this class is incomplete and will be modified for the next release
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class BodyLocation implements Serializable {
    private String photoID;
    private float xCoordinate;
    private float yCoordinate;

    public BodyLocation (String photoID, float xCoordinate, float yCoordinate) {
        this.photoID = photoID;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }


    /**
     * this method removes a body location
     *
     * @param location string of body location
     */
    public void removeBodyLocation(String location) {
        if (this.bodyLocations.get(location) != null) {
            bodyLocations.put(location, false);
        }
    }

    /**
     * This method returns a body location
     *
     * @param location  string of body location
     * @return          boolean
     */
    public Boolean getLocation(String location){
            if (this.bodyLocations.get(location) != null) {
               Boolean present = this.bodyLocations.get(location);
               return present;
            }
            return false;
    }

    public float getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public float getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

}
