package com.Product2_Q.Product2_Q.bootstrap;

import com.Product2_Q.Product2_Q.Interfaces.repository.ProductRepository;
import com.Product2_Q.Product2_Q.model.Product;
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
import java.util.Objects;

@Component
@Profile("bootstrap")
public class ProductBootstrap implements CommandLineRunner {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    @Autowired
    private ProductRepository productRepository;

    int page = 0;
    String response;

    @Override
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void run(String... args) throws JsonProcessingException {
        System.out.println(" [x] Requesting products from recovery system");
        do {
            response = (String) template.convertSendAndReceive(exchange.getName(), "rpc", page);
            if (response != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                List<Product> pt = objectMapper.readValue(response, new TypeReference<>() {
                });
                for (int i = 0; i <= pt.size() - 1; i++) {
                    productRepository.save(pt.get(i));
                }
                System.out.println(" [.] Got '" + response + "'");
                page++;
            }
        }while (!Objects.equals(response, "[ ]"));

    }
}
