package com.Product1_C.Product1_C.services;


import com.Product1_C.Product1_C.RabbitMQ.RabbitMQPublisher;
import com.Product1_C.Product1_C.model.Product;
import com.Product1_C.Product1_C.repository.Product2Repository;
import com.Product1_C.Product1_C.repository.ProductRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Random;


@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private Product2Repository productRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQPublisher jsonProducer;


    @Override
    public Product create(Product pt) throws IOException, InterruptedException {
        Product internalProduct = repository.getBySku(pt.getSku());
        if(internalProduct == null){
            Boolean existProduct = productRepository.existProduct(pt.getSku());
            if(!existProduct){
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(pt);
                jsonProducer.sendJsonMessage(json);
                return repository.save(pt);
            }
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT,"Product already exist");
    }





}
