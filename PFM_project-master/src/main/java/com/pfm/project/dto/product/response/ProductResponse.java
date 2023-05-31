package com.pfm.project.dto.product.response;

import com.pfm.project.domain.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "업소 음식 정보")
public class ProductResponse {
    @Schema(description = "음식 이름")
    private String productName;

    @Schema(description = "음식 가격")
    private int price;

    public ProductResponse(Product product) {
        this.productName = product.getProductName();
        this.price = product.getPrice();
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }
}
