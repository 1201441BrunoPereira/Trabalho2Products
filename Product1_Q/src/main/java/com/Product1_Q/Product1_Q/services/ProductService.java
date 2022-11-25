package com.Product1_Q.Product1_Q.services;

import com.Product1_Q.Product1_Q.model.Product;
import com.Product1_Q.Product1_Q.model.ProductDTO;
import java.awt.image.BufferedImage;
import java.util.List;

public interface ProductService {

    Object getBySku(String sku);

    //Object internalGetBySku (String sku) throws Exception;

    List<Product> getBySkuOrDesignation (String skuOrDesignation);

    //List<Product> internalGetBySkuOrDesignation (String skuOrDesignation);

    List<ProductDTO> getCatalog();

    BufferedImage generateCode128BarcodeImage(String barcodeText);

}
