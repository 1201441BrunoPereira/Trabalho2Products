package com.ProductRecovery.ProductRecovery.services;

import com.ProductRecovery.ProductRecovery.Interfaces.repository.ProductRepository;
import com.ProductRecovery.ProductRecovery.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp{

    @Autowired
    private ProductRepository repository;

    public void createByOther(String product) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product pt = objectMapper.readValue(product, Product.class);
        repository.save(pt);
    }

    public String getProducts(int page) throws JsonProcessingException {
        List<Product> productList = repository.getAllByPage(PageRequest.of(page,10));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(productList);
        System.out.println(" [.] Returned " + json);
        return json;
    }


}
