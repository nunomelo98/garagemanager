package com.nm.garagemanager.webhook.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nm.garagemanager.webhook.usecase.RegisterEntryUseCase;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class webhookController {

    // private final RegisterEntryUseCase registerEntryUseCase;
    // private final RegisterParkedUseCase registerParkedUseCase;
    // private final private final RegisterExitUseCase registerExitUseCase;

    // @PostMapping("/entry")
    // public ResponseEntity<String> handleEntry(@RequestBody EntryEventDTO dto) {
    //     return registerEntryUseCase.execute(dto);
    // }

}
