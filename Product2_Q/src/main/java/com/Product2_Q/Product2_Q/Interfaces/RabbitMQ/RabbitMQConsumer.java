package com.Product2_Q.Product2_Q.Interfaces.RabbitMQ;

import com.Product2_Q.Product2_Q.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private ProductService service;

    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String product) throws JsonProcessingException {
        service.createByOther(product);
        System.out.println("Creating product in Database");
    }


}
