package com.Product2_Q.Product2_Q;

import com.Product2_Q.Product2_Q.model.Product;
import com.Product2_Q.Product2_Q.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;




@SpringBootApplication
public class Product2QApplication {

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Product2QApplication.class, args);
	}

	@Bean
	public Queue myQueue() {
		return new Queue("myQueue", false);
	}

	@RabbitListener(queues = "myQueue")
	public void listen(String in) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Product pt = objectMapper.readValue(in, Product.class);
		productRepository.save(pt);
		System.out.println("Message read from myQueue : " + in);
	}
}
