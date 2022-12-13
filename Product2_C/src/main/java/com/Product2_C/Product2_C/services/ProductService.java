package com.Product2_C.Product2_C.services;

import com.Product2_C.Product2_C.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ProductService {

    Product create(Product pt) throws JsonProcessingException;

}
