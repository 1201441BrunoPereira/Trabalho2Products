package com.Product1_Q.Product1_Q.RabbitMQ;

import com.Product1_Q.Product1_Q.model.Product;
import com.Product1_Q.Product1_Q.repository.ProductRepository;
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
