package com.Product1_Q.Product1_Q.services;



import com.Product1_Q.Product1_Q.model.Product;
import com.Product1_Q.Product1_Q.model.ProductDTO;
import com.Product1_Q.Product1_Q.repository.Product2Repository;
import com.Product1_Q.Product1_Q.repository.ProductRepository;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private Product2Repository productRepository;

    @Override
    public List<ProductDTO> getCatalog(){
        return repository.getCatalog();
    }

    @Override
    public Object getBySku(final String sku) throws IOException, InterruptedException {
        Optional<Product> productOptional = repository.findById(sku);
        boolean isPresent = productOptional.isPresent();
        if(!isPresent){
            return productRepository.getProduct(sku);
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getBySkuOrDesignation(String skuOrDesignation) throws IOException, InterruptedException {
        List<Product> products = repository.getBySkuOrDesignation(skuOrDesignation);
        if(products.isEmpty()){
            return productRepository.getProductBySkuOrDesignation(skuOrDesignation);
        }else
            return products;
    }

    @Override
    public BufferedImage generateCode128BarcodeImage(String barcodeText) {
        Code128Bean barcodeGenerator = new Code128Bean();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        barcodeGenerator.generateBarcode(canvas, barcodeText);
        return canvas.getBufferedImage();
    }

}
