package com.pfm.project.dto.store.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class SearchStoreByWordReqeust {
    private String storeName;

    @Schema(example = "삼성동")
    private String address;

    private int page; //default = 0

    public SearchStoreByWordReqeust(String storeName, String address, int page) {
        this.storeName = storeName;
        this.address = address;
        this.page = page;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getAddress() {
        return address;
    }

    public int getPage() {
        return page;
    }
}
