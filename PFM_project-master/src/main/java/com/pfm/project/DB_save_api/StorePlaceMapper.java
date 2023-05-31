package com.pfm.project.DB_save_api;

import com.pfm.project.domain.store.Store;
import lombok.Builder;

public class StorePlaceMapper {
    private final Store store;
    private final PlaceApiDTO place;

    @Builder
    public StorePlaceMapper(Store store, PlaceApiDTO place) {
        this.store = store;
        this.place = place;
    }

    public Store getStore() {
        return store;
    }

    public PlaceApiDTO getPlace() {
        return place;
    }
}
