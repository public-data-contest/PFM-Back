package com.pfm.project.dto.store.response;

import com.pfm.project.dto.place.response.PlaceResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "업소 간단 정보")
public class StoreBriefInfoResponse {
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



    public StoreBriefInfoResponse(StoreBriefInfo store) {
        this.storeId = store.getStoreId();
        this.storeName =store.getStoreName();
        this.storeType =store.getStoreType();
        this.storePride =store.getStorePride();
        this.storeAddress =store.getStoreAddress();
        this.place = new PlaceResponse(store.getLatitude(),store.getLongitude());
    }




    @Builder
    public StoreBriefInfoResponse(
            Long storeId,
            String storeName,
            int storeType,
            String storePride,
            String storeAddress,
            PlaceResponse place
    ) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeType = storeType;
        this.storePride = storePride;
        this.storeAddress = storeAddress;
        this.place = place;
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
}
