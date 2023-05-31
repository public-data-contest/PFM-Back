package com.pfm.project.dto.store.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class SearchStoreByCategoryReqeust {
    private int storeType;

    @Schema(example = "삼성동")
    private String address;

    private int page; //default = 0

    public SearchStoreByCategoryReqeust(int storeType, String address, int page) {
        this.storeType = storeType;
        this.address = address;
        this.page = page;
    }

    public int getStoreType() {
        return storeType;
    }

    public String getAddress() {
        return address;
    }

    public int getPage() {
        return page;
    }
}
