package com.nm.garagemanager.webhook.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nm.garagemanager.repository.GarageRepository;
import com.nm.garagemanager.repository.SpotRepository;

@Service
public class GarageService {
    private final GarageRepository garageRepository;
    private final SpotRepository spotRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public GarageService(GarageRepository garageRepository, SpotRepository spotRepository ){
        this.garageRepository = garageRepository;
        this.spotRepository = spotRepository;

    }


    public void fetchAndStoreGarageData() {
        String url = "http://localhost:8080/garage";

        GarageResponse response = restTemplate.getForObject(url, GarageResponse.class);

        if(response != null){
            garageRepository.saveAll(response.getGarage());
            spotRepository.saveAll(response.getSpots());
            System.out.println("Dados guardados com sucesso.");
        }else{
            System.err.println("falha ao obter os dados");
        }

    }
}
