package com.pfm.project.dto.place.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "업소 좌표")
public class PlaceResponse {
    @Schema(description = "업소 위도", example = "0.0")
    private double latitude;
    @Schema(description = "업소 경도", example = "0.0")
    private double longitude;

    @Builder
    public PlaceResponse(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
