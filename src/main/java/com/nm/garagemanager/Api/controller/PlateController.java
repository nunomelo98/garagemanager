package com.nm.garagemanager.Api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nm.garagemanager.Api.dto.inbound.PlateStatusRequestDTO;
import com.nm.garagemanager.Api.dto.outbound.PlateStatusResponseDTO;
import com.nm.garagemanager.Api.usecase.GetPlateStatusUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/plate")
@RequiredArgsConstructor
public class PlateController {

    private final GetPlateStatusUseCase getPlateStatusUseCase;

    @PostMapping("/plate-status")
    public ResponseEntity<PlateStatusResponseDTO> getStatus(@Valid @RequestBody  PlateStatusRequestDTO request) {

        PlateStatusResponseDTO response = getPlateStatusUseCase.execute(request);
        return ResponseEntity.ok(response);
    }
}