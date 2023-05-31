package com.pfm.project.DB_save_api;

import com.pfm.project.domain.place.Place;
import com.pfm.project.domain.product.Product;
import com.pfm.project.domain.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DBSaveService {
    private final API_Store_Repository storeRepository;
    private final API_Product_Repository productRepository;
    private final API_Place_Repository placeRepository;

    @Autowired
    public DBSaveService(API_Store_Repository storeRepository, API_Product_Repository productRepository, API_Place_Repository placeRepository) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.placeRepository = placeRepository;
    }

    @Transactional
    public void saveStoreDB(List<Store> req) {
        storeRepository.saveAll(req);
    }

    @Transactional
    public void saveProductDB(List<ProductApiDTO> req) {

        List<Product> saveProducts = new ArrayList<>();

        for (ProductApiDTO p: req) {
            Optional<Store> store = storeRepository.findById(p.getId());

            if (store.isPresent()) {
                Product product = Product.builder()
                        .price(p.getProductPrice())
                        .store(store.get())
                        .productName(p.getProductName()).build();

                saveProducts.add(product);
            }
        }

        productRepository.saveAll(saveProducts);
    }

    @Transactional
    public void savePlaceDB(List<PlaceApiDTO> req) {
        List<Place> savePlaces = new ArrayList<>();

        for (PlaceApiDTO p: req) {
            Place place = Place.builder()
                    .latitude(p.getLatitude())
                    .longitude(p.getLongitude())
                    .store(storeRepository.findById(p.getId()).orElseThrow()).build();

            savePlaces.add(place);
        }

        placeRepository.saveAll(savePlaces);
    }
}
