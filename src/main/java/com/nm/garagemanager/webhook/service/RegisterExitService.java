package com.nm.garagemanager.webhook.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.nm.garagemanager.entity.ParkingSpot;
import com.nm.garagemanager.entity.SectorStatus;
import com.nm.garagemanager.entity.VehicleRecord;
import com.nm.garagemanager.exceptions.SectorStatusNotFoundException;
import com.nm.garagemanager.exceptions.SpotNotFoundException;
import com.nm.garagemanager.exceptions.VehicleRecordNotFoundException;
import com.nm.garagemanager.helpers.PaymentCalculator;
import com.nm.garagemanager.repository.SectorStatusRepository;
import com.nm.garagemanager.repository.SpotRepository;
import com.nm.garagemanager.repository.VehicleRecorRepository;
import com.nm.garagemanager.webhook.dto.WebhookEventDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterExitService {

    private final VehicleRecorRepository recordRepository;
    private final SpotRepository spotRepository;
    private final SectorStatusRepository sectorStatusRepository;

@Transactional
    public void handleExit(WebhookEventDTO dto) {
        VehicleRecord record = findVehicleRecord(dto);
        updateExitInfo(record, dto.getExitTime());
        freeParkingSpot(record);
        updateSectorStatus(record.getSector());
    }

    private VehicleRecord findVehicleRecord(WebhookEventDTO dto) {
        return recordRepository.findTopByLicensePlateOrderByEntryTimeDesc(dto.getLicensePlate())
                .orElseThrow(() -> new VehicleRecordNotFoundException(dto.getLicensePlate()));
    }

    private void updateExitInfo(VehicleRecord record, LocalDateTime exitTime) {
        record.setExitTime(exitTime);

        if (record.getEntryTime() != null && exitTime != null) {
            double total = PaymentCalculator.calculatePayment(
                    record.getEntryTime(), exitTime, record.getPaidAmount());
            record.setPaidAmount(total);
        }

        record.setPaid(true);
        recordRepository.save(record);
    }

    private void freeParkingSpot(VehicleRecord record) {
        Long spotId = record.getSpotId();
        if (spotId == null) return;

        ParkingSpot spot = spotRepository.findById(spotId)
                .orElseThrow(() -> new SpotNotFoundException(spotId));
        spot.setOccupied(false);
        spotRepository.save(spot);
    }

    private void updateSectorStatus(String sector) {
        SectorStatus status = sectorStatusRepository.findBySector(sector)
                .orElseThrow(() -> new SectorStatusNotFoundException(sector));

        int newOccupancy = Math.max(0, status.getCurrent_occupancy() - 1);
        status.setCurrent_occupancy(newOccupancy);
        status.setIs_closed(false);
        sectorStatusRepository.save(status);
    }
}
