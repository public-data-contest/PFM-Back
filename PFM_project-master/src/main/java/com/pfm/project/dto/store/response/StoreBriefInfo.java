package com.pfm.project.dto.store.response;

import com.pfm.project.dto.place.response.PlaceResponse;

public interface StoreBriefInfo {
    Long getStoreId();

    String getStoreName();

    int getStoreType();

    String getStorePride();

    String getStoreAddress();

    double getLatitude();

    double getLongitude();
}
