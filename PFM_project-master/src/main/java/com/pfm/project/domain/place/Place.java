package com.pfm.project.domain.place;

import com.pfm.project.domain.store.Store;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PLACE")
public class Place {
    @Id
    @Column(name = "store_id")
    private long id;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @OneToOne(targetEntity = Store.class)
    @MapsId
    @JoinColumn(name = "store_id")
    private Store store;

    @Builder
    public Place(double latitude, double longitude, Store store) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.store = store;
    }

    @Override
    public String toString() {
        return "Place{" +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}