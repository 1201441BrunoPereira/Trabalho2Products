package com.Product2_Q.Product2_Q.services;

import com.Product2_Q.Product2_Q.model.Product;
import com.Product2_Q.Product2_Q.model.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ProductService {

    Object getBySku(String sku);

    //Object internalGetBySku (String sku) throws Exception;

    List<Product> getBySkuOrDesignation (String skuOrDesignation);

    //List<Product> internalGetBySkuOrDesignation (String skuOrDesignation);

    List<ProductDTO> getCatalog();

    BufferedImage generateCode128BarcodeImage(String barcodeText);

    void createByOther(String pt) throws JsonProcessingException;

}
