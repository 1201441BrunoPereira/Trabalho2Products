package com.Product1_Q.Product1_Q.Interfaces.repository;



import com.Product1_Q.Product1_Q.model.Product;
import com.Product1_Q.Product1_Q.model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT f FROM Product f where f.designation like %:skuOrDesignation% or f.sku = :skuOrDesignation")
    List<Product> getBySkuOrDesignation(@Param("skuOrDesignation") String skuOrDesignation);

    @Query("SELECT  f FROM Product f ")
    List <ProductDTO> getCatalog();
}
