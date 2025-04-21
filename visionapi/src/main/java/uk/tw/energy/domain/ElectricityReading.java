package uk.tw.energy.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;


/**
 * @param reading kW
 */
public record ElectricityReading(Instant time, BigDecimal reading) {
    public ElectricityReading {
        Objects.requireNonNull(time, "Time must not be null");
        Objects.requireNonNull(reading, "Time must not be null");
        if(reading.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Reading must be greater than zero");
        }
    }

}
