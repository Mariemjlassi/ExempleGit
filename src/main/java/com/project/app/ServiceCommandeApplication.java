package com.project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ServiceCommandeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCommandeApplication.class, args);
	}

}
