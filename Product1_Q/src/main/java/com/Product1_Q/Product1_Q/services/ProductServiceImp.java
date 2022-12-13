package com.Product1_Q.Product1_Q.services;

import com.Product1_Q.Product1_Q.model.Product;
import com.Product1_Q.Product1_Q.model.ProductDTO;
import com.Product1_Q.Product1_Q.Interfaces.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.awt.image.BufferedImage;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductDTO> getCatalog(){
        return repository.getCatalog();
    }

    @Override
    public Object getBySku(final String sku) {
        return repository.findById(sku).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found"));
    }

    @Override
    public List<Product> getBySkuOrDesignation(String skuOrDesignation) {
        List <Product> product = repository.getBySkuOrDesignation(skuOrDesignation);

        if (product.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found");
        }else{
            return product;
        }
    }

    @Override
    public BufferedImage generateCode128BarcodeImage(String barcodeText) {
        Code128Bean barcodeGenerator = new Code128Bean();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        barcodeGenerator.generateBarcode(canvas, barcodeText);
        return canvas.getBufferedImage();
    }

    @Override
    public void createByOther(String product) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product pt = objectMapper.readValue(product, Product.class);
        repository.save(pt);
    }

}
