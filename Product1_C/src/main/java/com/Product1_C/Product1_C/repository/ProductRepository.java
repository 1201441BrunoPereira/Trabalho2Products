package com.Product1_C.Product1_C.repository;


import com.Product1_C.Product1_C.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {

}
