package com.pfm.project.service;

import com.pfm.project.data.StoreRepository;
import com.pfm.project.domain.store.Store;
import com.pfm.project.dto.store.response.StoreDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class StoreDetailService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreDetailService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreDetailResponse findStoreDetail(Long storeId) {
        Optional<Store> result = storeRepository.findById(storeId);



        if (result.isEmpty()) {
            throw new NotFoundException("Not Found - " + storeId);
        }

        Store store = result.get();

        return new StoreDetailResponse(store);
    }

}
