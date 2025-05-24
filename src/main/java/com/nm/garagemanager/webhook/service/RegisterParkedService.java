package com.nm.garagemanager.webhook.service;



import org.springframework.stereotype.Service;

import com.nm.garagemanager.entity.ParkingSpot;
import com.nm.garagemanager.entity.VehicleRecord;
import com.nm.garagemanager.repository.SpotRepository;
import com.nm.garagemanager.repository.VehicleRecorRepository;
import com.nm.garagemanager.webhook.dto.WebhookEventDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterParkedService {

    private final VehicleRecorRepository recordRepository;
     private final SpotRepository spotRepository;

     @Transactional
     public void handleParked(WebhookEventDTO dto){

        ParkingSpot spot = spotRepository.findByLatAndLng(dto.getLat(), dto.getLng())
                .orElseThrow(() -> new RuntimeException("Spot not found"));

        if (spot.getOccupied()) {
        throw new RuntimeException("Spot is already occupied");
    }

        spot.setOccupied(true);
        spotRepository.save(spot);

        VehicleRecord record = recordRepository.findTopByLicensePlateOrderByEntryTimeDesc(dto.getLicensePlate())
                .orElseThrow(() -> new RuntimeException("Vehicle record not found"));
        record.setSpotId(spot.getId());
        record.setLat(dto.getLat());
        record.setLng(dto.getLng());
        record.setActive(true);
        recordRepository.save(record);

     }

}
