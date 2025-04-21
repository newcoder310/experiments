package uk.tw.energy.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public record MeterReadingRequest(String smartMeterId, List<ElectricityReadingRequest> electricityReadings) {
    public MeterReadingRequest {
        if(StringUtils.isBlank(smartMeterId)) {
            throw new IllegalArgumentException("Smart meter ID must not be blank");
        }
        if (electricityReadings==null || electricityReadings.isEmpty()) {
            throw new IllegalArgumentException("Electricity readings must not be empty or null ");
        }
    }
}
