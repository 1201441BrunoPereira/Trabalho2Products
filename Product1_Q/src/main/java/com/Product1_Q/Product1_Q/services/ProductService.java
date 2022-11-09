package com.Product1_Q.Product1_Q.services;




import com.Product1_Q.Product1_Q.model.Product;
import com.Product1_Q.Product1_Q.model.ProductDTO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    Object getBySku(String sku) throws IOException, InterruptedException;

    List<Product> getBySkuOrDesignation (String skuOrDesignation) throws IOException, InterruptedException;

    List<ProductDTO> getCatalog() throws IOException, InterruptedException;
    Product create(Product pt) throws IOException;

    BufferedImage generateCode128BarcodeImage(String barcodeText);

}
