package com.nm.garagemanager.Api.service;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.nm.garagemanager.Api.dto.inbound.RevenueRequestDTO;
import com.nm.garagemanager.Api.dto.outbound.RevenueResponseDTO;
import com.nm.garagemanager.repository.VehicleRecorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevenueService {
    private final VehicleRecorRepository vehicleRecordRepository;

    public RevenueResponseDTO getRevenue(RevenueRequestDTO dto) {


        LocalDate date = dto.getData();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        double amount = vehicleRecordRepository
                .sumRevenueByDateAndSector(startOfDay, endOfDay, dto.getSector());

        return RevenueResponseDTO.builder()
                .amount(amount)
                .currency("BRL")
                .timestamp(LocalDateTime.now())
                .build();
    }

}
