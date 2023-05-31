package com.pfm.project.service;

import com.pfm.project.data.NaverGeocodingRepository;
import com.pfm.project.data.StoreRepository;
import com.pfm.project.dto.address.AddressResponse;
import com.pfm.project.dto.place.response.PlaceResponse;
import com.pfm.project.dto.store.request.SearchStoreByMapReqeust;
import com.pfm.project.dto.store.response.StoreBriefInfo;
import com.pfm.project.dto.store.response.StoreBriefInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private final StoreRepository storeRepository;
    private final NaverGeocodingRepository naverGeocodingRepository;
    private int getOffsetByPage(int page) {
        return (page) * 20;
    }



    @Autowired
    public SearchService(StoreRepository storeRepository, NaverGeocodingRepository naverGeocodingRepository) {
        this.storeRepository = storeRepository;
        this.naverGeocodingRepository = naverGeocodingRepository;
    }

    public AddressResponse searchUserAddress(double latitude, double longitude) {
        String address = naverGeocodingRepository.searchUserAddress(latitude, longitude);

        return new AddressResponse(address);
    }

    public List<StoreBriefInfoResponse> searchStoreByMap(SearchStoreByMapReqeust searchStoreByMapReqeust) {
        String userInput = searchStoreByMapReqeust.getStoreName();
        Integer storeType = searchStoreByMapReqeust.getStoreType();
        List<StoreBriefInfo> sqlResults = null;
        List<StoreBriefInfoResponse> result = new ArrayList<>();

        if (userInput != null) {
            sqlResults = storeRepository.findAllByMapOrderByDistanceWithWord(
                    searchStoreByMapReqeust.getMinLatitude(),
                    searchStoreByMapReqeust.getMaxLatitude(),
                    searchStoreByMapReqeust.getMinLongitude(),
                    searchStoreByMapReqeust.getMaxLongitude(),
                    searchStoreByMapReqeust.getUserPlace().getLatitude(),
                    searchStoreByMapReqeust.getUserPlace().getLongitude(),
                    searchStoreByMapReqeust.getStoreName(),
                    getOffsetByPage(searchStoreByMapReqeust.getPage())
            );

        } else if (storeType != null) {
            sqlResults = storeRepository.findAllByMapOrderByDistanceWithCategory(
                    searchStoreByMapReqeust.getMinLatitude(),
                    searchStoreByMapReqeust.getMaxLatitude(),
                    searchStoreByMapReqeust.getMinLongitude(),
                    searchStoreByMapReqeust.getMaxLongitude(),
                    searchStoreByMapReqeust.getUserPlace().getLatitude(),
                    searchStoreByMapReqeust.getUserPlace().getLongitude(),
                    searchStoreByMapReqeust.getStoreType(),
                    getOffsetByPage(searchStoreByMapReqeust.getPage())
            );


        } else {
            sqlResults = storeRepository.findAllByMapOrderByDistance(
                    searchStoreByMapReqeust.getMinLatitude(),
                    searchStoreByMapReqeust.getMaxLatitude(),
                    searchStoreByMapReqeust.getMinLongitude(),
                    searchStoreByMapReqeust.getMaxLongitude(),
                    searchStoreByMapReqeust.getUserPlace().getLatitude(),
                    searchStoreByMapReqeust.getUserPlace().getLongitude(),
                    getOffsetByPage(searchStoreByMapReqeust.getPage())
            );
        }

        for (StoreBriefInfo s : sqlResults) {
            result.add(
                    StoreBriefInfoResponse.builder()
                            .storeId(s.getStoreId())
                            .storeName(s.getStoreName())
                            .storeType(s.getStoreType())
                            .storePride(s.getStorePride())
                            .storeAddress(s.getStoreAddress())
                            .place(
                                    PlaceResponse.builder()
                                            .latitude(s.getLatitude())
                                            .longitude(s.getLongitude())
                                            .build()
                            )
                            .build()
            );
        }

        return result;

    }

    // 홈페이지 가게검색 출력
    @Transactional
    public List<StoreBriefInfo> homeSearch(String search,String address, int page) {

        return storeRepository.homeSearch(search,address,page*20)
                .orElseThrow(IllegalArgumentException :: new);
    }

    @Transactional
    public List<StoreBriefInfo> searchCategory(int category, String address, int page) {

        return storeRepository.SearchCategory( category,address,page*20)
                .orElseThrow(IllegalArgumentException :: new);
    }



}
