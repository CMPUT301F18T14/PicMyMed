package com.example.picmymedcode.Model;

import java.io.Serializable;

public class BodyLocationPhoto extends Photo implements Serializable {
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
