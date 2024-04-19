package com.petshop.breed;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Breeds microservice REST API Documentation",
				description = "PetShop Breeds microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Luis Ribeiro",
						email = "luisfelipe.alves@gmail.com",
						url = "https://www.linkedin.com/in/luisfelipealves/"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)
public class BreedApplication {

	public static void main(String[] args) {
		SpringApplication.run(BreedApplication.class, args);
	}

}
