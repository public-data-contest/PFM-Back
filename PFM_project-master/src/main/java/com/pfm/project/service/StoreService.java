package com.pfm.project.service;


import com.pfm.project.data.PlaceRepository;
import com.pfm.project.data.ProductRepository;
import com.pfm.project.data.StoreRepository;
import com.pfm.project.domain.store.Store;
import com.pfm.project.dto.store.response.StoreBriefInfo;
import com.pfm.project.dto.store.response.StoreDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    private final PlaceRepository placeRepository;

    private final StoreRepository storeRepository;

    private final ProductRepository productRepository;

    @Autowired
    public StoreService(PlaceRepository placeRepository, StoreRepository storeRepository, ProductRepository productRepository) {

        this.placeRepository = placeRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    // 홈페이지 접속시 card출력
    @Transactional
    public List<StoreBriefInfo> coordinates(double longitude, double latitude) {

        List<StoreBriefInfo> place = placeRepository.homeCard(longitude,latitude)
                .orElseThrow(IllegalArgumentException :: new);


        return place;


    }


    // 홈페이지에서 카드쪽 더보기 클릭시
    @Transactional
    public List<StoreBriefInfo> allSelect(String address, double longitude, double latitude, int page) {

        List<StoreBriefInfo> stores = storeRepository.AllSelect(address,longitude,latitude,page*20)
                .orElseThrow(IllegalArgumentException :: new);

        return stores;
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
