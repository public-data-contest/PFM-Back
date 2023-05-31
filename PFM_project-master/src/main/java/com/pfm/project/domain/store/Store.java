package com.pfm.project.domain.store;

import com.pfm.project.domain.place.Place;
import com.pfm.project.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Table(name="STORE")
public class Store {
    // 업소 아이디
    @Id
    @Column(name="store_id")
    private Long storeId;

    // 업소명
    @Column(name = "store_name", nullable = false)
    private String storeName;

    // 분류 코드
    @Column(name = "store_type", nullable = false)
    private int storeType;

    // 업소 전화 번호
    @Column(name = "store_number")
    private String storeNumber;

    // 찾아 오시는 길
    @Column(name = "store_waytocome")
    private String storeWayToCome;

    // 자랑거리
    @Column(name = "store_pride")
    private String storePride;

    // 업소 사진
    @Column(name = "store_url")
    private String storeUrl;

    // 업소 정보
    @Column(name = "store_info")
    private String storeInfo;

    // 업소 주소
    @Column(name = "store_address", nullable = false)
    private String storeAddress;

    @OneToOne(targetEntity = Place.class,mappedBy = "store")
    private Place place;


    @OneToMany(targetEntity = Product.class,mappedBy = "store")
    private List<Product> product;


    //https://cheese10yun.github.io/spring-builder-pattern/
    @Builder
    public Store(
            Long storeId,
            String storeName,
            int storeType,
            String storeNumber,
            String storeWayToCome,
            String storePride,
            String storeUrl,
            String storeInfo,
            String storeAddress
    ) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeType = storeType;
        this.storeNumber = storeNumber;
        this.storeWayToCome = storeWayToCome;
        this.storePride = storePride;
        this.storeUrl = storeUrl;
        this.storeInfo = storeInfo;
        this.storeAddress = storeAddress;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", storeType=" + storeType +
                ", storeNumber='" + storeNumber + '\'' +
                ", storeWayToCome='" + storeWayToCome + '\'' +
                ", storePride='" + storePride + '\'' +
                ", storeUrl='" + storeUrl + '\'' +
                ", storeInfo='" + storeInfo + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", place=" + place +
                ", product=" + product +
                '}';
    }
}
