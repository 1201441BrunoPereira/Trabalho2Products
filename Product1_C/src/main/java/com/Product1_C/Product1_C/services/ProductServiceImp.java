package com.Product1_C.Product1_C.services;


import com.Product1_C.Product1_C.model.Product;
import com.Product1_C.Product1_C.repository.Product2Repository;
import com.Product1_C.Product1_C.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;


@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private Product2Repository productRepository;

    @Override
    public Product create(Product pt) throws IOException, InterruptedException {
        Product internalProduct = repository.getBySku(pt.getSku());
        if(internalProduct == null){
            Boolean existProduct = productRepository.existProduct(pt.getSku());
            if(!existProduct){
                return repository.save(pt);
            }
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT,"Product already exist");
    }


}
