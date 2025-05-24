package com.nm.garagemanager.Api.usecase;

import org.springframework.stereotype.Component;

import com.nm.garagemanager.Api.dto.inbound.PlateStatusRequestDTO;
import com.nm.garagemanager.Api.dto.outbound.PlateStatusResponseDTO;
import com.nm.garagemanager.Api.service.PlateStatusService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetPlateStatusUseCase {
    private final PlateStatusService service;

    public PlateStatusResponseDTO execute(PlateStatusRequestDTO request){
        return service.getPlateStatus(request);
    }

}
