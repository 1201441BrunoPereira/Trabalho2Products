package com.Product2_C.Product2_C.Interfaces.repository;

import com.Product2_C.Product2_C.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT f FROM Product f where f.sku= :sku")
    Product getBySku(@Param("sku") String sku);

}
