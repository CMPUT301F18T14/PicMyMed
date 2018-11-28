package com.example.picmymedcode.Model;

public class BodyLocationPhoto extends Photo {
    private String label;

    public BodyLocationPhoto(String filepath) {
        super(filepath);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
