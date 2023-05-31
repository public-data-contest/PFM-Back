package com.pfm.project.dto.store_detail;

import com.pfm.project.dto.product.response.ProductResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "업소 자세한 정보")
public class StoreDetailResponseDTO {

    @Schema(description = "업소 정보")
    private String storeInfo;
    @Schema(description = "업소 전화 번호")
    private String storeNumber;

    @Schema(description = "찾아 오시는 길")
    private String storeWayToCome;

    @ArraySchema()
    private List<ProductResponse> products;

    public StoreDetailResponseDTO(String storeInfo, String storeNumber, String storeWayToCome, List<ProductResponse> products) {
        this.storeInfo = storeInfo;
        this.storeNumber = storeNumber;
        this.storeWayToCome = storeWayToCome;
        this.products = products;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public String getStoreInfo() {
        return storeInfo;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public String getStoreWayToCome() {
        return storeWayToCome;
    }


}
