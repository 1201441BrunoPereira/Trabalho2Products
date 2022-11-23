package com.Product1_C.Product1_C;


import org.h2.tools.Server;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class Product1CApplication {

	public static void main(String[] args) {
		SpringApplication.run(Product1CApplication.class, args);
	}

	@Bean
	public Queue myQueue() {
		return new Queue("myQueue", false);
	}

}
