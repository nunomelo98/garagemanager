package com.nm.garagemanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nm.garagemanager.webhook.service.GarageService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Garage Manager API",
        version = "1.0",
        description = "Documentação da API para gestão de garagem"
    )
)

public class GaragemanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaragemanagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadGarageData(GarageService garageService) {
		return args -> {
			garageService.fetchAndStoreGarageData();
		};
	}

}
