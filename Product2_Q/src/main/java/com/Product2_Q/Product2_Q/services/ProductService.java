package com.Product2_Q.Product2_Q.services;

import com.Product2_Q.Product2_Q.model.Product;
import com.Product2_Q.Product2_Q.model.ProductDTO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    Object getBySku(String sku) throws IOException, InterruptedException;

    Object internalGetBySku (String sku);

    List<Product> getBySkuOrDesignation (String skuOrDesignation) throws IOException, InterruptedException;

    List<Product> internalGetBySkuOrDesignation (String skuOrDesignation);

    List<ProductDTO> getCatalog();

    BufferedImage generateCode128BarcodeImage(String barcodeText);

}
