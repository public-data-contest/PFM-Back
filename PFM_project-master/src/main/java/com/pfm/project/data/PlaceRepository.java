package com.pfm.project.data;

import com.pfm.project.domain.place.Place;
import com.pfm.project.dto.store.response.StoreBriefInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place,Long> {


    @Query(value = "select s.store_id as storeId," +
            " s.store_address as storeAddress," +
            " s.store_name as storeName," +
            " s.store_pride as storePride," +
            " s.store_type as storeType," +
            " p.latitude as latitude," +
            " p.longitude as longitude," +
            " ST_DISTANCE_SPHERE(POINT(:longitude,:latitude), POINT(p.longitude,p.latitude)) AS dist" +
            " from place as p" +
            " left outer join store as s" +
            " on p.store_id=s.store_id" +
            " Order BY dist" +
            " limit 0,7",
            nativeQuery = true)
    Optional<List<StoreBriefInfo>> homeCard(@Param("longitude") double longitude, @Param("latitude")double latitude);





}
