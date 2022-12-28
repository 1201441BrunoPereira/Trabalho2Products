package com.ProductRecovery.ProductRecovery.Interfaces.repository;

import com.ProductRecovery.ProductRecovery.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT f FROM Product f")
    List<Product> getAllByPage(Pageable pageable);
}
