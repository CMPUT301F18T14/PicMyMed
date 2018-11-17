package com.example.mukha.picmymedcode.Model;


import java.util.HashMap;

public class BodyLocation {
    private HashMap<String, Boolean> bodyLocations;

    public BodyLocation() {
        bodyLocations = new HashMap<String, Boolean>();
        this.initializeLocations();
    }
    public void initializeLocations() {
        this.bodyLocations.put("Face", false);
        this.bodyLocations.put("Right Shoulder", false);
        this.bodyLocations.put("Left Shoulder", false);
        this.bodyLocations.put("Right Arm", false);
        this.bodyLocations.put("Left Arm", false);
        this.bodyLocations.put("Upper Chest", false);
        this.bodyLocations.put("Torso", false);
        this.bodyLocations.put("Right Leg", false);
        this.bodyLocations.put("Left Leg", false);

    }

    public void addBodyLocation(String location) {
        if (this.bodyLocations.get(location) != null) {
            bodyLocations.put(location, true);
        }

    }
    public void removeBodyLocation(String location) {
        if (this.bodyLocations.get(location) != null) {
            bodyLocations.put(location, false);
        }
    }
    public Boolean getLocation(String location){
            if (this.bodyLocations.get(location) != null) {
               Boolean present = this.bodyLocations.get(location);
               return present;
            }
            return false;
    }



}
