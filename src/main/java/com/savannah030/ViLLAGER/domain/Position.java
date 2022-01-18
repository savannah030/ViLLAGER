package com.savannah030.ViLLAGER.domain;

public class Position {
    private double latitude;
    private double longitude;

    public Position(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.latitude;
    }

    public Position getPosition(){
        return this;
    }
}
