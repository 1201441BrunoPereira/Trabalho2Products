package com.Product1_Q.Product1_Q.controllers;

import com.Product1_Q.Product1_Q.model.Product;
import com.Product1_Q.Product1_Q.model.ProductDTO;
import com.Product1_Q.Product1_Q.services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductServiceImp service;

    @GetMapping
    public ResponseEntity<Product> getBySku(@RequestParam("sku") final String sku) throws IOException, InterruptedException {
        final Product product = (Product) service.getBySku(sku);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/catalog")
    public Iterable<ProductDTO> getCatalog(){
        return service.getCatalog();
    }

    @GetMapping(value = "/search")
    public Iterable<Product> getBySkuOrDesignation(@RequestParam("skuOrDesignation") final String skuOrDesignation) throws IOException, InterruptedException {
        return service.getBySkuOrDesignation(skuOrDesignation);
    }

    @GetMapping(value = "/{sku}/barcode128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueCode128Barcode(@PathVariable("sku") final String sku) throws Exception {
        return ResponseEntity.ok(service.generateCode128BarcodeImage(sku));
    }


}
