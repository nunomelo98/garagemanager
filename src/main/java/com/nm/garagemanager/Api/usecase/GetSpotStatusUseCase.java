package com.nm.garagemanager.Api.usecase;

import org.springframework.stereotype.Component;

import com.nm.garagemanager.Api.dto.inbound.SpotStatusRequestDTO;
import com.nm.garagemanager.Api.dto.outbound.SpotStatusResponseDTO;
import com.nm.garagemanager.Api.service.SpotStatusService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetSpotStatusUseCase {
    private final SpotStatusService service;

    public SpotStatusResponseDTO execute(SpotStatusRequestDTO request){
        return service.getSpotStatus(request);
    }


}