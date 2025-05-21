package com.nm.garagemanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nm.garagemanager.webhook.service.GarageService;

@SpringBootApplication
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
