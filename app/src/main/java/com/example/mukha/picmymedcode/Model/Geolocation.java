package com.example.mukha.picmymedcode.Model;

import android.location.Location;

public class Geolocation {
    private double xCoordinate;
    private double yCoordinate;

    public Geolocation(double xCoordinate, double yCoordinate) {
        this.setxCoordinate(xCoordinate);
        this.setyCoordinate(yCoordinate);
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
