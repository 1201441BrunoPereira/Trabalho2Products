package com.Product1_C.Product1_C.services;


import com.Product1_C.Product1_C.model.Product;
import com.Product1_C.Product1_C.repository.ProductRepository;
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

    @Override
    public Product create(Product pt) throws IOException {
        return repository.save(pt);
    }


}
