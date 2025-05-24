package com.nm.garagemanager.webhook.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nm.garagemanager.webhook.dto.WebhookEventDTO;
import com.nm.garagemanager.webhook.usecase.WebhookUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class webhookController {


    private final WebhookUseCase webhookUseCase;
    @Operation(
        summary = "Recebe eventos de entrada, estacionamento ou saída",
        description = "Aceita três tipos de eventos: ENTRY, PARKED e EXIT.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "application/json",
                examples = {
                    @ExampleObject(
                        name = "Entrada na garagem",
                        summary = "Evento ENTRY",
                        value = "{\n" +
                                "  \"license_plate\": \"ZUL0001\",\n" +
                                "  \"entry_time\": \"2025-01-01T12:00:00.000Z\",\n" +
                                "  \"event_type\": \"ENTRY\"\n" +
                                "}"
                    ),
                    @ExampleObject(
                        name = "Entrada na vaga",
                        summary = "Evento PARKED",
                        value = "{\n" +
                                "  \"license_plate\": \"ZUL0001\",\n" +
                                "  \"lat\": -23.561684,\n" +
                                "  \"lng\": -46.655981,\n" +
                                "  \"event_type\": \"PARKED\"\n" +
                                "}"
                    ),
                    @ExampleObject(
                        name = "Saída da garagem",
                        summary = "Evento EXIT",
                        value = "{\n" +
                                "  \"license_plate\": \"\",\n" +
                                "  \"exit_time\": \"2025-01-01T12:00:00.000Z\",\n" +
                                "  \"event_type\": \"EXIT\"\n" +
                                "}"
                    )
                }
            )
        )
    )

    @PostMapping("")
    public  ResponseEntity<?> execute(@RequestBody WebhookEventDTO dto) {
     try {
            webhookUseCase.processWebhook(dto);
            return ResponseEntity.ok("Evento processado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
        }
    }



}
