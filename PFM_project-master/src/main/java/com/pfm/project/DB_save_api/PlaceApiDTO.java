package com.pfm.project.DB_save_api;

import lombok.Builder;

public class PlaceApiDTO {
    private final long id;
    private final double latitude;
    private final double longitude;

    @Builder
    public PlaceApiDTO(long id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "PlaceApiDTO{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
