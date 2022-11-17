package com.Product2_Q.Product2_Q.repository;

import com.Product2_Q.Product2_Q.model.Product;
import com.Product2_Q.Product2_Q.model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT f FROM Product f where f.sku= :sku")
    Product getBySku(@Param("sku") String sku);

    @Query("SELECT f FROM Product f where f.designation like %:skuOrDesignation% or f.sku = :skuOrDesignation")
    List<Product> getBySkuOrDesignation(@Param("skuOrDesignation") String skuOrDesignation);

    @Query("SELECT  f FROM Product f ")
    List <ProductDTO> getCatalog();
}
