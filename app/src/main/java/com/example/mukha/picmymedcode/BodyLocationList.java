package com.example.mukha.picmymedcode;

import java.util.ArrayList;

public class BodyLocationList {
    private ArrayList<BodyLocation> bodyLocations;

    public BodyLocationList() {
        this.bodyLocations = new ArrayList<BodyLocation>();
    }

    public void addBodyLocation(BodyLocation bodyLocation) {
        this.bodyLocations.add(bodyLocation);
    }

    public void deleteBodyLocation(int index) {
        
    }
}

