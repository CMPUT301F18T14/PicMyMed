/*
 * PlaceInformation
 *
 * 1.2
 *
 * November 16, 2018
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
package com.example.picmymedmaphandler.Model;

import com.google.android.gms.maps.model.LatLng;

/**
 * PlaceInformation gets a location on a maps information
 *
 * @author  Umer, Apu, Ian, Shawna, Eenna, Debra
 * @version 1.2, 02/12/18
 * @since   1.1
 */
public class PlaceInformation {

    private CharSequence name;
    private CharSequence address;
    private String id;
    private LatLng latlng;

    /**
     * initiates the class
     *
     * @param name      CharSequence
     * @param address   CharSequence
     * @param id        String
     * @param latlng    LatLng
     */
    public PlaceInformation(CharSequence name, CharSequence address, String id, LatLng latlng){
        this.name = name;
        this.address = address;
        this.id = id;
        this.latlng = latlng;
    }

    public PlaceInformation(){}

    /**
     * Gets the ID
     *
     * @return  id
     */
    public String getId() {
        return id;
    }

    /**
     * sets the id
     *
     * @param id    id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * gets the address
     *
     * @return  CharSequence
     */
    public CharSequence getAddress() {
        return address;
    }

    /**
     * sets the address
     *
     * @param address   String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the name
     *
     * @return  CharSequence
     */
    public CharSequence getName() {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name  String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets coordinates
     *
     * @return  Latlng
     */
    public LatLng getLatlng() {
        return latlng;
    }

    /**
     * sets the coordinates
     *
     * @param latlng    LatLng
     */
    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    /**
     * Converts to string
     *
     * @return  string
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
