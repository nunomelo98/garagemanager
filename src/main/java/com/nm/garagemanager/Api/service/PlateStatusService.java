package com.nm.garagemanager.Api.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.nm.garagemanager.Api.dto.inbound.PlateStatusRequestDTO;
import com.nm.garagemanager.Api.dto.outbound.PlateStatusResponseDTO;
import com.nm.garagemanager.entity.VehicleRecord;
import com.nm.garagemanager.exceptions.PlateStatusNotFoundException;
import com.nm.garagemanager.helpers.FormatHourDuration;
import com.nm.garagemanager.repository.VehicleRecorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlateStatusService {

    private final VehicleRecorRepository vehicleRecordRepository;

    public PlateStatusResponseDTO getPlateStatus(PlateStatusRequestDTO dto) {
        VehicleRecord record = vehicleRecordRepository.findTopByLicensePlateOrderByEntryTimeDesc(dto.getLicense_plate())
                .orElseThrow(() -> new PlateStatusNotFoundException(dto.getLicense_plate()));

                 Duration duration = Duration.between(record.getEntryTime(), LocalDateTime.now());
                 String formattedDuration = FormatHourDuration.formatDuration(duration);

        return PlateStatusResponseDTO.builder()
                .licensePlate(record.getLicensePlate())
                .priceUntilNow(record.getPaidAmount())
                .entryTime(record.getEntryTime())
                .timeParked(formattedDuration)
                .lat(record.getLat())
                .lng(record.getLng())
                .build();
    }
}
