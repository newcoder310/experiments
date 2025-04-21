package uk.tw.energy.domain;







import org.apache.commons.lang3.StringUtils;

import java.util.List;


public record MeterReadings(String smartMeterId, List<ElectricityReading> electricityReadings) {
    public MeterReadings {
        StringUtils.isBlank(smartMeterId);
        if (electricityReadings.isEmpty()) {
            throw new IllegalArgumentException("Electricity readings must not be empty");
        }
    }
}
