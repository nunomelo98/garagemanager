package com.nm.garagemanager.Api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.nm.garagemanager.Api.dto.inbound.SpotStatusRequestDTO;
import com.nm.garagemanager.Api.dto.outbound.SpotStatusResponseDTO;
import com.nm.garagemanager.entity.ParkingSpot;
import com.nm.garagemanager.entity.VehicleRecord;
import com.nm.garagemanager.exceptions.SpotNotFoundforlatlngException;
import com.nm.garagemanager.repository.SpotRepository;
import com.nm.garagemanager.repository.VehicleRecorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpotStatusService {
    private final SpotRepository parkingSpotRepository;
    private final VehicleRecorRepository vehicleRecordRepository;

    public SpotStatusResponseDTO getSpotStatus(SpotStatusRequestDTO dto) {
        ParkingSpot spot = parkingSpotRepository.findByLatAndLng(dto.getLat(), dto.getLng())
                .orElseThrow(() -> new SpotNotFoundforlatlngException(dto.getLat(), dto.getLng()));

        VehicleRecord record = vehicleRecordRepository.findTopBySpotIdAndExitTimeIsNull(spot.getId()).orElse(null);

        return SpotStatusResponseDTO.builder()
                .ocupied(spot.getOccupied())
                .entryTime(record != null ? record.getEntryTime() : null)
                .timeParked(record != null ? LocalDateTime.now() : null)
                .build();
    }

}
