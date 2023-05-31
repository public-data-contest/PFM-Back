package com.pfm.project.DB_save_api;

import com.pfm.project.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface API_Product_Repository extends JpaRepository<Product, Long> {
}
