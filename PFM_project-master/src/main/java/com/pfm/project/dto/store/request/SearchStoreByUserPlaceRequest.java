package com.pfm.project.dto.store.request;

import com.pfm.project.dto.place.request.PlaceRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class SearchStoreByUserPlaceRequest {
    private PlaceRequest userPlace;

    @Schema(example = "삼성동")
    private String address;

    private int page; //default = 0

    public SearchStoreByUserPlaceRequest(PlaceRequest userPlace, String address, int page) {
        this.userPlace = userPlace;
        this.address = address;
        this.page = page;
    }

    public PlaceRequest getUserPlace() {
        return userPlace;
    }

    public String getAddress() {
        return address;
    }

    public int getPage() {
        return page;
    }
}
