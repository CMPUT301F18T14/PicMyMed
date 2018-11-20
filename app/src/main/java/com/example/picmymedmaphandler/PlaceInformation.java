/*
 * PlaceInformation
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
package com.example.picmymedmaphandler;

import com.google.android.gms.maps.model.LatLng;

/**
 * PlaceInformation gets the location selected on a map by the user
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.1, 16/11/18
 * @since   1.1
 */
public class PlaceInformation {

    private CharSequence name;
    private CharSequence address;
    private String id;
    private LatLng latlng;

    /**
     * Initializes the class
     *
     * @param name      CharSequence
     * @param address   CharSequence
     * @param id        String
     * @param latlng    Latlng
     */
    public PlaceInformation(CharSequence name, CharSequence address, String id, LatLng latlng){
        this.name = name;
        this.address = address;
        this.id = id;
        this.latlng = latlng;
    }

    /**
     * Method gets the id of the location
     *
     * @return  id
     */
    public String getId() {
        return id;
    }

    /**
     * Method sets the id of the location
     *
     * @param id    String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method will get the address
     *
     * @return  address
     */
    public CharSequence getAddress() {
        return address;
    }

    /**
     * Method will set the address
     *
     * @param address   String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method gets the location name
     *
     * @return  name
     */
    public CharSequence getName() {
        return name;
    }

    /**
     * Method set the name of the location
     *
     * @param name  String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method will get the lattitude
     *
     * @return  latlng
     */
    public LatLng getLatlng() {
        return latlng;
    }

    /**
     * Method will set the lattitude
     *
     * @param latlng    Latlng
     */
    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    /**
     * Method returns string of place information
     *
     * @return  String
     */
    @Override
    public String toString() {
        return "PlaceInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                ", latlng=" + latlng +
                '}';
    }
}
