package com.example.picmymedmaphandler.Model;

import com.google.android.gms.maps.model.LatLng;

public class PlaceInformation {

    private CharSequence name;
    private CharSequence address;
    private String id;
    private LatLng latlng;

    public PlaceInformation(CharSequence name, CharSequence address, String id, LatLng latlng){
        this.name = name;
        this.address = address;
        this.id = id;
        this.latlng = latlng;
    }

    public PlaceInformation(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CharSequence getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

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
