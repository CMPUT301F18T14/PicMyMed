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

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Geolocation class handles the x and y coordinates of a physical location of map
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class Geolocation {
    private double latitude;
    private double longitude;
    private String locationName;

    /**
     * An empty constructor
     */
    public Geolocation() {

    }

    /**
     * This method initializes the variables
     *
     * @param latitude   double
     * @param longitude   double
     */
    public Geolocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Method gets latitude of a location on a map
     *
     * @return  latitude
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Method sets latitude method sets the latitude of a location
     *
     * @param latitude   double
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Method gets longitude returns the longitude of a location
     *
     * @return  longitude
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Method sets longitude sets the longitude of a location
     *
     * @param longitude   double
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Method gets location name
     *
     * @return          String of location name
     */
    public String getLocationName() { return this.locationName; }

    /**
     * This method sets the location name
     *
     * @param context           Context of the activity calling this function
     * @throws IOException      throws an input output exception
     */
    public void setLocationName(Context context) throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        addresses = geocoder.getFromLocation(this.latitude, this.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        this.locationName = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//        String city = addresses.get(0).getLocality();
//        String state = addresses.get(0).getAdminArea();
//        String country = addresses.get(0).getCountryName();
//        String postalCode = addresses.get(0).getPostalCode();
//        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
    }
}
