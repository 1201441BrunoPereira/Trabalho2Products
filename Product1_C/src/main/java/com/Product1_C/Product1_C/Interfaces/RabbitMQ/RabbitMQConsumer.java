package com.Product1_C.Product1_C.Interfaces.RabbitMQ;

import com.Product1_C.Product1_C.services.ProductService;
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
