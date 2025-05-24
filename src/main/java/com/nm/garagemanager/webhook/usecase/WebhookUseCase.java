package com.nm.garagemanager.webhook.usecase;

import org.springframework.stereotype.Component;

import com.nm.garagemanager.webhook.dto.WebhookEventDTO;
import com.nm.garagemanager.webhook.service.RegisterEntryService;
import com.nm.garagemanager.webhook.service.RegisterExitService;
import com.nm.garagemanager.webhook.service.RegisterParkedService;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WebhookUseCase {
    private final RegisterEntryService registerEntryService;
    private final RegisterParkedService registerParkedService;
    private final RegisterExitService registerExitService;

    public void processWebhook(WebhookEventDTO dto){
        String eventType = dto.getEventType();
        switch (eventType) {
            case "ENTRY":
                    registerEntryService.handleEntry(dto);

                break;
            case "PARKED":
                registerParkedService.handleParked(dto);
                break;
            case "EXIT":
                registerExitService.handleExit(dto);
                break;
            default:
                throw new IllegalArgumentException("Tipo de evento desconhecido: " + eventType);
        }
    }

}
