package com.nm.garagemanager.Api.usecase;

import org.springframework.stereotype.Component;

import com.nm.garagemanager.Api.dto.inbound.RevenueRequestDTO;
import com.nm.garagemanager.Api.dto.outbound.RevenueResponseDTO;
import com.nm.garagemanager.Api.service.RevenueService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetRevenueUseCase {
    private final RevenueService service;

    public RevenueResponseDTO execute(RevenueRequestDTO request){
        return service.getRevenue(request);
    }

}
