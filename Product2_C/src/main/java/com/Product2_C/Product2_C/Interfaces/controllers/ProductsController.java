package com.Product2_C.Product2_C.Interfaces.controllers;


import com.Product2_C.Product2_C.model.Product;
import com.Product2_C.Product2_C.services.ProductServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductServiceImp service;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody final Product pt) throws JsonProcessingException {
        return this.service.create(pt);
    }

}