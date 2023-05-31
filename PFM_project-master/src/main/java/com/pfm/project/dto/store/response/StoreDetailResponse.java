package com.pfm.project.dto.store.response;

import com.pfm.project.domain.product.Product;
import com.pfm.project.domain.store.Store;
import com.pfm.project.dto.place.response.PlaceResponse;
import com.pfm.project.dto.product.response.ProductResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "업소 자세한 정보")
public class StoreDetailResponse {
    @Schema(description = "업소 아이디")
    private Long storeId;

    @Schema(description = "업소 이름")
    private String storeName;

    @Schema(description = "분류 코드")
    private int storeType;

    @Schema(description = "자랑거리")
    private String storePride;

    @Schema(description = "업소 주소")
    private String storeAddress;

    @Schema(description = "업소 좌표")
    private PlaceResponse place;


    @Schema(description = "업소 정보")
    private String storeInfo;
    @Schema(description = "업소 전화 번호")
    private String storeNumber;

    @Schema(description = "찾아 오시는 길")
    private String storeWayToCome;

    @Schema(description = "업소 사진")
    private String storeUrl;

    @ArraySchema()
    private List<ProductResponse> products;

//    @Builder
//    public StoreDetailResponse(String storeInfo, String storeNumber, String storeWayToCome, List<ProductResponse> products, String storeUrl) {
//        this.storeInfo = storeInfo;
//        this.storeNumber = storeNumber;
//        this.storeWayToCome = storeWayToCome;
//        this.storeUrl = storeUrl;
//        this.products = products;
//    }

    public StoreDetailResponse(Store store) {
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product p : store.getProduct()) {
            ProductResponse productResponse = new ProductResponse(p);
            productResponses.add(productResponse);

        }
        this.storeId = store.getStoreId();
        this.storeName = store.getStoreName();
        this.storeType = store.getStoreType();
        this.storePride = store.getStorePride();
        this.storeAddress = store.getStoreAddress();
        this.place = PlaceResponse.builder().latitude(store.getPlace().getLatitude()).longitude(store.getPlace().getLongitude()).build();
        this.storeInfo = store.getStoreInfo();
        this.storeNumber = store.getStoreNumber();
        this.storeWayToCome = store.getStoreWayToCome();
        this.storeUrl = store.getStoreUrl();
        this.products = productResponses;
    }

    public Long getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getStoreType() {
        return storeType;
    }

    public String getStorePride() {
        return storePride;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public PlaceResponse getPlace() {
        return place;
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

    public String getStoreUrl() {
        return storeUrl;
    }
}
