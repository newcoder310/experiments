package com.game.mancala.kalah.handler;

import com.game.mancala.kalah.action.Result;
import com.game.mancala.kalah.presentation.data.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Class that handles the exceptions
 */
@Component
@Lazy
@Slf4j
public class FailureHandler {

    /**
     * Memthod to handle exceptions. Checks if the status is failure and stops processing if so that invalid game
     * information is not saved.
     * @param result - {@link Result}
     * @param dto - The {@link GameDTO}
     */
public void handle(Result result, GameDTO dto){
        if (!Result.SUCCESS.equals(result)) {
            log.error("The Action has failed {} ", result.getDeclaringClass());
            dto.setStopProcessing(true);
        }
    }
}
