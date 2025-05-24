package com.nm.garagemanager.Api.dto.inbound;

import java.time.LocalDate;


import lombok.Data;

@Data
public class RevenueRequestDTO {
    private LocalDate data;
    private String sector;

}
