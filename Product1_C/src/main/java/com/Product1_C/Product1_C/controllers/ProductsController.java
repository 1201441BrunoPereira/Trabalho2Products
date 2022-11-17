package com.Product1_C.Product1_C.controllers;


import com.Product1_C.Product1_C.model.Product;
import com.Product1_C.Product1_C.services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductServiceImp service;

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody final Product pt) throws IOException, InterruptedException {
        return this.service.create(pt);
    }

}