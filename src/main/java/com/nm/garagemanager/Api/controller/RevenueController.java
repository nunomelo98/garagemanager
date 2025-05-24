package com.nm.garagemanager.Api.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nm.garagemanager.Api.dto.inbound.RevenueRequestDTO;
import com.nm.garagemanager.Api.dto.outbound.RevenueResponseDTO;
import com.nm.garagemanager.Api.usecase.GetRevenueUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/revenue")
@RequiredArgsConstructor
public class RevenueController {
    private final GetRevenueUseCase getRevenueUseCase;

    @PostMapping
    public ResponseEntity<RevenueResponseDTO> getRevenue(@RequestBody RevenueRequestDTO request){
        RevenueResponseDTO response = getRevenueUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

}
