package com.myRetail.myRetailAPI;

import com.myRetail.myRetailAPI.models.Product;
import com.myRetail.myRetailAPI.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
public class MyRetailApiApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(MyRetailApiApplication.class, args);
	}

	/*
	* Populate MongoDB with values
	* */
	@Override
	public void run(String... args) throws Exception
	{
		productRepository.deleteAll();

		Map currentPrice = new HashMap();
		currentPrice.put("value", 13.49);
		currentPrice.put("currency_code", "USD");
		Product product = new Product (13860428, currentPrice);

		productRepository.save(product);
	}
}
