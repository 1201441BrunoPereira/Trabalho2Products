package com.Product1_C.Product1_C.services;

import com.Product1_C.Product1_C.RabbitMQ.RabbitMQPublisher;
import com.Product1_C.Product1_C.model.Product;
import com.Product1_C.Product1_C.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private RabbitMQPublisher jsonProducer;


    @Override
    public Product create(Product pt) throws JsonProcessingException {
        Product internalProduct = repository.getBySku(pt.getSku());
        if(internalProduct == null){
            jsonProducer.sendJsonMessage(pt);
            return repository.save(pt);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT,"Product already exist");
    }





}
