package com.game.mancala.kalah.presentation.resources;

import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.presentation.data.MoveFailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Exception handler class.
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the general exceptions
     * @param ex {@link Exception} that occurred
     * @param request The request that was received
     * @return The response entity built for exception handling
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<MoveFailureResponse> handleAllExceptions(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                MoveFailureResponse.builder()
                .url(request.getPathInfo())
                .message(ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles kalah exceptions.
     * @param ex {@link KalahException} that occurred
     * @param request The request that was received
     * @return The response entity built for exception handling
     */
    @ExceptionHandler(KalahException.class)
    public final ResponseEntity<MoveFailureResponse> handleKalahExceptions(KalahException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                MoveFailureResponse.builder()
                .url(request.getRequestURI())
                .errorCode(ex.getErrorCode())
                .message(ex.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
}
