package com.Product1_C.Product1_C;


import com.Product1_C.Product1_C.model.Product;
import com.Product1_C.Product1_C.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Product1CApplication {

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Product1CApplication.class, args);
	}

	@Bean
	public Queue createdProduct1() {
		return new Queue("createdProduct1", false);
	}

	//@Bean
	//public Queue createdProduct2() {
		//return new Queue("createdProduct2", false);
	//}


	@RabbitListener(queues = "createdProduct1")
	public void listenCreatedProduct2(String in) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Product pt = objectMapper.readValue(in, Product.class);
		productRepository.save(pt);
		System.out.println("Message read from myQueue : " + in);
	}

}
