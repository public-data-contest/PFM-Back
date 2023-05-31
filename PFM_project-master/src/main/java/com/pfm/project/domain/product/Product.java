package com.pfm.project.domain.product;

import com.pfm.project.domain.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "PRODUCT")
public class Product {
    // 상품 아이디
    @Id
    @GeneratedValue
    @Column(name = "product_id", nullable = false)
    private int id;

    // 상품명
    @Column(name="product_name", nullable = false)
    private String productName;

    // 상품 가격
    @Column(name="price", nullable = false)
    private int price;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    @Builder
    public Product(String productName, int price, Store store) {
        this.store = store;
        this.price = price;
        this.productName =productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
