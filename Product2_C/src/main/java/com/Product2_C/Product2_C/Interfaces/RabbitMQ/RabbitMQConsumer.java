package com.Product2_C.Product2_C.Interfaces.RabbitMQ;

import com.Product2_C.Product2_C.Interfaces.repository.ProductRepository;
import com.Product2_C.Product2_C.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @Autowired
    private ProductRepository productRepository;

    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void consumeJsonMessage(String product) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product pt = objectMapper.readValue(product, Product.class);
        productRepository.save(pt);
        System.out.println("Creating product in Database with sku:" + pt.getSku());
    }
}
