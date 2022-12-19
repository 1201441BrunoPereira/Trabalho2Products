package com.ProductRecovery.ProductRecovery.Interfaces.repository;

import com.ProductRecovery.ProductRecovery.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
