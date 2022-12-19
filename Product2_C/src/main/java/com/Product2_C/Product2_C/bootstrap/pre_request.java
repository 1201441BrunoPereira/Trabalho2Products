package com.Product2_C.Product2_C.bootstrap;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("bootstrap")
public class pre_request implements CommandLineRunner {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    int start = 0;

    @Override
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void run(String... args){
        System.out.println(" [x] Requesting products from recovery system(" + start + ")");
        String response = (String) template.convertSendAndReceive(exchange.getName(), "rpc", start++);
        System.out.println(" [.] Got '" + response + "'");
    }
}
