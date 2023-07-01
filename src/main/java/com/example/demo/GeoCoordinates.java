package com.example.demo;

import lombok.Data;

@Data
public class GeoCoordinates {

    private double latitude;
    private double longitude;

    public GeoCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
