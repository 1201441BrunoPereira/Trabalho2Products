package com.ProductRecovery.ProductRecovery.Interfaces.RabbitMQ;

import com.ProductRecovery.ProductRecovery.services.ProductServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private ProductServiceImp productServiceImp;


    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String product) throws JsonProcessingException {
        productServiceImp.createByOther(product);
        System.out.println("Creating product in Database");
    }


    @RabbitListener(queues = "productRecovery.request")
    public String productRecovery(int page) throws JsonProcessingException {
        System.out.println(" [x] Received request for product recovery");
        return productServiceImp.getProducts(page);
    }

}
