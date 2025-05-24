package com.nm.garagemanager.Api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nm.garagemanager.Api.dto.inbound.SpotStatusRequestDTO;
import com.nm.garagemanager.Api.dto.outbound.SpotStatusResponseDTO;
import com.nm.garagemanager.Api.usecase.GetSpotStatusUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/spot")
@RequiredArgsConstructor
public class SpotController {

    private final GetSpotStatusUseCase getSpotStatusUseCase;

    @PostMapping("/spot-status")
    public ResponseEntity<SpotStatusResponseDTO> getSpotStatus(@Valid @RequestBody SpotStatusRequestDTO request){
        SpotStatusResponseDTO response = getSpotStatusUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

}
