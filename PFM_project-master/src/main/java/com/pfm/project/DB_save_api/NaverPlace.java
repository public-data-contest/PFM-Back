package com.pfm.project.DB_save_api;

import lombok.Builder;

public class NaverPlace {
    private final String address;
    private final double latitude;
    private final double longitude;

    @Builder
    public NaverPlace(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "NaverPlace{" +
                "address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
