package com.nm.garagemanager.helpers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nm.garagemanager.entity.GarageSector;
import com.nm.garagemanager.repository.GarageRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GarageSectorSynchronizer {

    private final GarageRepository garageRepository;

    public void syncSectors(List<GarageSector> sectorsFromApi) {
        for (GarageSector incomingSector : sectorsFromApi) {
            Optional<GarageSector> existingSectorOpt = garageRepository.findBySector(incomingSector.getSector());

            if (existingSectorOpt.isPresent()) {
                GarageSector existing = existingSectorOpt.get();
                existing.setBase_price(incomingSector.getBase_price());
                existing.setMax_capacity(incomingSector.getMax_capacity());
                existing.setOpen_hour(incomingSector.getOpen_hour());
                existing.setClose_hour(incomingSector.getClose_hour());
                existing.setDuration_limit_minutes(incomingSector.getDuration_limit_minutes());

                garageRepository.save(existing);
            } else {
                garageRepository.save(incomingSector);
            }
        }
    }
}

