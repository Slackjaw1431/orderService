package com.shopforhome.orderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
@EntityScan("com.shopforhome.orderService.entity")
@EnableJpaRepositories("com.shopforhome.orderService.repository")
@ComponentScan({ "com.shopforhome.orderService.configuration", "com.shopforhome.orderService.controller",
		"com.shopforhome.orderService.service" })
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
