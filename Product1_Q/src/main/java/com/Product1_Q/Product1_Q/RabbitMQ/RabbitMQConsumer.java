package com.Product1_Q.Product1_Q.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
    public void consumeJsonMessage(String product){
        System.out.println(product);
    }

}
