package com.Product1_Q.Product1_Q.Interfaces.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("product.created");
    }

    @Bean
    public Queue autoDeleteQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding(FanoutExchange fanout, Queue autoDeleteQueue) {
        return BindingBuilder.bind(autoDeleteQueue).to(fanout);
    }
}
