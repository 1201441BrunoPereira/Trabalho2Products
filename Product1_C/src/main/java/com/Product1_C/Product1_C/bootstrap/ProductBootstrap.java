package com.Product1_C.Product1_C.bootstrap;

import com.Product1_C.Product1_C.Interfaces.repository.ProductRepository;
import com.Product1_C.Product1_C.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Profile("bootstrap")
public class ProductBootstrap implements CommandLineRunner {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    @Autowired
    private ProductRepository productRepository;

    int start = 0;

    @Override
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void run(String... args) throws JsonProcessingException {
        System.out.println(" [x] Requesting products from recovery system(" + start + ")");
        String response = (String) template.convertSendAndReceive(exchange.getName(), "rpc", start++);
        if (response != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Product> pt = objectMapper.readValue(response, new TypeReference<>() {
            });
            for (int i = 0; i <= pt.size() - 1; i++) {
                productRepository.save(pt.get(i));
            }
        }
        System.out.println(" [.] Got '" + response + "'");
    }
}
