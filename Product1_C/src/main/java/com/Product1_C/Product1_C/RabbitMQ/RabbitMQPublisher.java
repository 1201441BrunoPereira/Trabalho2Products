package com.Product1_C.Product1_C.RabbitMQ;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQPublisher {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;

    public void sendJsonMessage(String product){
        template.convertAndSend(fanout.getName(), "", product);
    }

}
