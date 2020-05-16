package com.game.mancala.kalah.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The custom exception class that extends the Runtimeexception
 */
@Getter
public class KalahException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String serviceName;
    private int errorCode;
    private HttpStatus status;

    public KalahException() {}

    public KalahException(final String serviceName, final String message, int errorCode, HttpStatus status) {
        super(message);
        this.serviceName = serviceName;
        this.errorCode = errorCode;
        this.status = status;
    }

    public KalahException(final String message, int errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

}
