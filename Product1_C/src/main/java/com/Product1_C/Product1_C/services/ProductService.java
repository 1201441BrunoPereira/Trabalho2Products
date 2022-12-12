package com.Product1_C.Product1_C.services;


import com.Product1_C.Product1_C.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface ProductService {

    Product create(Product pt) throws JsonProcessingException;

}
