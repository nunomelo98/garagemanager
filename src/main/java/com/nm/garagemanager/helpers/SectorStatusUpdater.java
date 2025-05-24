package com.nm.garagemanager.helpers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.stereotype.Component;

import com.nm.garagemanager.entity.GarageSector;
import com.nm.garagemanager.entity.ParkingSpot;
import com.nm.garagemanager.entity.SectorStatus;
import com.nm.garagemanager.repository.SectorStatusRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SectorStatusUpdater {
    private final SectorStatusRepository sectorStatusRepository;


    public void updateStatuses(List<GarageSector> sectors, List<ParkingSpot> spots) {
        Map<String, Long> occupiedCountBySector = spots.stream()
                .filter(ParkingSpot::getOccupied)
                .collect(Collectors.groupingBy(ParkingSpot::getSector, Collectors.counting()));

        for (GarageSector sector : sectors) {
            String name = sector.getSector();
            long occupied = occupiedCountBySector.getOrDefault(name, 0L);
            boolean isClosed = occupied >= sector.getMax_capacity();

            SectorStatus status = sectorStatusRepository.findBySector(name)
                    .orElse(new SectorStatus());

            status.setSector(name);
            status.setCurrent_occupancy((int) occupied);
            status.setIs_closed(isClosed);

            sectorStatusRepository.save(status);
        }
    }

}
