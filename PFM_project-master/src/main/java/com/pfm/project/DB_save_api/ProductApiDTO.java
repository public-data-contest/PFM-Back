package com.pfm.project.DB_save_api;

import lombok.Builder;

public class ProductApiDTO {
    private final long id;
    private final String productName;
    private final int productPrice;

    @Builder
    public ProductApiDTO(long id, String productName, int productPrice) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }
}
