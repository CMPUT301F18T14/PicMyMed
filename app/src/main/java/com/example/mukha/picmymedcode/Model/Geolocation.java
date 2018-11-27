/*
 * Geolocation
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
/**
 * Geolocation class handles the x and y coordinates of a physical location of map
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class Geolocation {
    private double xCoordinate;
    private double yCoordinate;
    /**
     * This method initializes the variables
     *
     * @param xCoordinate   double
     * @param yCoordinate   double
     */
    public Geolocation(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    /**
     * Method gets x coordinate of a location on a map
     *
     * @return  xCoordinate
     */
    public double getxCoordinate() {
        return xCoordinate;
    }
    /**
     * Method sets x coordinate method sets the x coordinate of a location
     *
     * @param xCoordinate   double
     */
    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    /**
     * Method gets y coordinate returns the y coordinate of a location
     *
     * @return  yCoordinate
     */
    public double getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Method sets y coordinate sets the y coordinate of a location
     *
     * @param yCoordinate   yCoordinate
     */
    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
