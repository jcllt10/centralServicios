package com.juanca.usermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserMicroServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMicroServicioApplication.class, args);
	}

}
