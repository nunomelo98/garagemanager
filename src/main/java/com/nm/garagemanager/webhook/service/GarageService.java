package com.nm.garagemanager.webhook.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.nm.garagemanager.exceptions.GarageDataFetchException;
import com.nm.garagemanager.helpers.GarageSectorSynchronizer;
import com.nm.garagemanager.helpers.SectorStatusUpdater;
import com.nm.garagemanager.repository.SpotRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GarageService {



    private final SpotRepository spotRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final SectorStatusUpdater sectorStatusUpdater;
    private final GarageSectorSynchronizer garageSectorSynchronizer;


    @Transactional
    public void fetchAndStoreGarageData() {
        String url = "http://localhost:8080/garage";

        GarageResponse response = restTemplate.getForObject(url, GarageResponse.class);

        if (response == null) {
            throw new GarageDataFetchException("Failed to fetch data from garage API");
        }

        garageSectorSynchronizer.syncSectors(response.getGarage());
        // garageRepository.saveAll(response.getGarage());
        spotRepository.saveAll(response.getSpots());

        sectorStatusUpdater.updateStatuses(response.getGarage(), response.getSpots());

        System.out.println("Dados guardados com sucesso.");



    }
}
