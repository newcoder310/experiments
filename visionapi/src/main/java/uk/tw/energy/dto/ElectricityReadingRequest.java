package uk.tw.energy.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;

public record ElectricityReadingRequest(Instant time, BigDecimal reading) {



    public ElectricityReadingRequest {
        if (time == null) {
            throw new IllegalArgumentException("Time must not be null");
        }
        if (reading == null) {
            throw new IllegalArgumentException("Reading must not be null");
        }
        if (reading.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Reading must be greater than or equal to zero");
        }

    }
}
