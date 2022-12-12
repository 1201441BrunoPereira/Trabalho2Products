package com.Product1_Q.Product1_Q.services;

import com.Product1_Q.Product1_Q.model.Product;
import com.Product1_Q.Product1_Q.model.ProductDTO;
import java.awt.image.BufferedImage;
import java.util.List;

public interface ProductService {

    Object getBySku(String sku);


    List<Product> getBySkuOrDesignation (String skuOrDesignation);


    List<ProductDTO> getCatalog();

    BufferedImage generateCode128BarcodeImage(String barcodeText);

}
