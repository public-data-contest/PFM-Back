package com.pfm.project.data;

import com.pfm.project.domain.store.Store;
import com.pfm.project.dto.store.response.StoreBriefInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {

    @Query(value = "SELECT s.store_id as storeId," +
            " s.store_address as storeAddress," +
            " s.store_name as storeName," +
            " s.store_pride as storePride," +
            " s.store_type as storeType," +
            " p.latitude as latitude," +
            " p.longitude as longitude," +
            " ST_DISTANCE_SPHERE(POINT(:longitude,:latitude), POINT(p.longitude,p.latitude)) AS dist" +
            " FROM place AS p" +
            " left outer join store AS s" +
            " on p.store_id=s.store_id" +
            " WHERE s.store_address like %:address%" +
            " ORDER BY dist" +
            " LIMIT 20 OFFSET :page",
            nativeQuery = true)
    Optional<List<StoreBriefInfo>> AllSelect(
            @Param("address")String address, @Param("longitude") double longitude,
            @Param("latitude")double latitude, @Param("page") int page);


    @Query(value = "SELECT s.store_id as storeId," +
            " s.store_address as storeAddress," +
            " s.store_name as storeName," +
            " s.store_pride as storePride," +
            " s.store_type as storeType," +
            " p.latitude as latitude," +
            " p.longitude as longitude" +
            " FROM store AS s" +
            " inner join place AS p" +
            " on s.store_id = p.store_id" +
            " left join product AS pd" +
            " on s.store_id = pd.store_id" +
            " WHERE s.store_address like %:address%" +
            " AND (s.store_name like %:search% OR pd.product_name like %:search%) GROUP BY s.store_id" +
            " ORDER BY CASE WHEN s.store_name like %:search% then 1" +
            " WHEN pd.product_name like %:search% then 2" +
            " else 3 end, s.store_id" +
            " LIMIT 20 OFFSET :page",
            nativeQuery = true)
    Optional<List<StoreBriefInfo>> homeSearch( @Param("search") String search,
            @Param("address")String address, @Param("page") int page);

    @Query(value = "SELECT s.store_id as storeId," +
            " s.store_address as storeAddress," +
            " s.store_name as storeName," +
            " s.store_pride as storePride," +
            " s.store_type as storeType," +
            " p.latitude as latitude," +
            " p.longitude as longitude" +
            " FROM store AS s" +
            " inner join place AS p" +
            " on s.store_id = p.store_id" +
            " WHERE s.store_address like %:address%" +
            " AND s.store_type = :category" +
            " ORDER BY s.store_id" +
            " LIMIT 20 OFFSET :page",
            nativeQuery = true)
    Optional<List<StoreBriefInfo>> SearchCategory( @Param("category") int search,
                                               @Param("address")String address, @Param("page") int page);


    @Query("select s from Store as s join fetch s.place join fetch  s.product where s.storeId = :id")
    Optional<Store> findById(Long id);

    @Query(value = "select " +
            "s.store_id as storeId, s.store_name as storeName, s.store_type as storeType, s.store_pride as storePride, s.store_address as storeAddress, pl.latitude as latitude, pl.longitude as longitude, " +
            "ST_DISTANCE_SPHERE(POINT(:userLongitude,:userLatitude), Point(pl.longitude, pl.latitude)) as dist " +
            "from " +
            "store as s " +
            "inner join place as pl on s.store_id = pl.store_id " +
            "where pl.latitude between :minLatitude and :maxLatitude and pl.longitude between :minLongitude and :maxLongitude " +
            "group by s.store_id order by dist limit 20 offset :offset", nativeQuery = true
    )
    List<StoreBriefInfo> findAllByMapOrderByDistance(
            double minLatitude,
            double maxLatitude,
            double minLongitude,
            double maxLongitude,
            double userLatitude,
            double userLongitude,
            int offset
    );


    @Query(value = "select s.store_id as storeId, s.store_name as storeName, s.store_type as storeType, s.store_pride as storePride, s.store_address as storeAddress, pl.latitude as latitude, pl.longitude as longitude, " +
            "ST_DISTANCE_SPHERE(POINT(:userLongitude,:userLatitude), Point(pl.longitude,pl.latitude)) as dist " +
            "from store as s inner join place as pl on s.store_id = pl.store_id " +
            "left join product as pr on s.store_id = pr.store_id " +
            "where pl.latitude between :minLatitude and :maxLatitude and pl.longitude between :minLongitude and :maxLongitude " +
            "and (s.store_name like concat(\"%\", :userInput, \"%\") or pr.product_name like concat(\"%\", :userInput, \"%\")) " +
            "group by s.store_id order by " +
            "case when s.store_name like concat(\"%\", :userInput, \"%\") then 1 " +
            "when pr.product_name like concat(\"%\", :userInput, \"%\") then 2 else 3 end, dist limit 20 offset :offset", nativeQuery = true)
    List<StoreBriefInfo> findAllByMapOrderByDistanceWithWord(
            double minLatitude,
            double maxLatitude,
            double minLongitude,
            double maxLongitude,
            double userLatitude,
            double userLongitude,
            String userInput,
            int offset
    );

    @Query(value = "select s.store_id as storeId, s.store_name as storeName, s.store_type as storeType, s.store_pride as storePride, s.store_address as storeAddress, pl.latitude as latitude, pl.longitude as longitude, " +
            "ST_DISTANCE_SPHERE(POINT(:userLongitude,:userLatitude), Point(pl.longitude,pl.latitude)) as dist " +
            "from store as s inner join place as pl on s.store_id = pl.store_id " +
            "where pl.latitude between :minLatitude and :maxLatitude and pl.longitude between :minLongitude and :maxLongitude " +
            "and s.store_type = :storeType " +
            "group by s.store_id order by " +
            "dist limit 20 offset :offset", nativeQuery = true)
    List<StoreBriefInfo> findAllByMapOrderByDistanceWithCategory(
            double minLatitude,
            double maxLatitude,
            double minLongitude,
            double maxLongitude,
            double userLatitude,
            double userLongitude,
            int storeType,
            int offset
    );
}
