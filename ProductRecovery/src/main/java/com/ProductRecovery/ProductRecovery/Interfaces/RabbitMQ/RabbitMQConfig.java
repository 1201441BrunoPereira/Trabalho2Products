package com.ProductRecovery.ProductRecovery.Interfaces.RabbitMQ;

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

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("productRecovery.request");
    }

    @Bean
    public Queue queue() {
        return new Queue("productRecovery.request");
    }

    @Bean
    public Binding binding2(DirectExchange exchange,
                           Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("rpc");
    }

}
