package com.nm.garagemanager.webhook.service;

import org.springframework.stereotype.Service;

import com.nm.garagemanager.entity.GarageSector;
import com.nm.garagemanager.entity.ParkingSpot;
import com.nm.garagemanager.entity.SectorStatus;
import com.nm.garagemanager.entity.VehicleRecord;
import com.nm.garagemanager.helpers.PriceCalculation;
import com.nm.garagemanager.repository.GarageRepository;
import com.nm.garagemanager.repository.SectorStatusRepository;
import com.nm.garagemanager.repository.SpotRepository;
import com.nm.garagemanager.repository.VehicleRecorRepository;
import com.nm.garagemanager.webhook.dto.WebhookEventDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterEntryService {
    private final VehicleRecorRepository recordRepository;
    private final SpotRepository spotRepository;
    private final GarageRepository sectorRepository;
    private final SectorStatusRepository sectorStatusRepository;
    private final PriceCalculation priceCalculation;

    @Transactional
    public void handleEntry(WebhookEventDTO dto){

        ParkingSpot spot = spotRepository.findFirstByOccupiedFalse().orElseThrow(() -> new RuntimeException("No free spots available"));

        String sector = spot.getSector();
        SectorStatus status = sectorStatusRepository.findBySector(sector).orElseThrow(() -> new RuntimeException("Sector not found"));

        GarageSector garageSector = sectorRepository.findBySector(sector).orElseThrow(() -> new RuntimeException("Garage sector not found"));

        int occupancy = status.getCurrent_occupancy();
        double price = garageSector.getBase_price();
        int maxCapacity = garageSector.getMax_capacity();

        double calculatedPrice = priceCalculation.calculatePrice(price, occupancy, maxCapacity);

        if (occupancy +1 >= garageSector.getMax_capacity()){
            status.setIs_closed(true);
        }

        status.setCurrent_occupancy(occupancy + 1);
        sectorStatusRepository.save(status);

        VehicleRecord record = new VehicleRecord();
        record.setLicensePlate(dto.getLicensePlate());
        record.setEntryTime(dto.getEntryTime());
        record.setSector(sector);
        record.setPaidAmount(calculatedPrice);
        recordRepository.save(record);







    }


}
