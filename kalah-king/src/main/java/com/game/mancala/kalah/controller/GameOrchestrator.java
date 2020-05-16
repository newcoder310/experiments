package com.game.mancala.kalah.controller;

import com.game.mancala.kalah.action.Action;
import com.game.mancala.kalah.action.Result;
import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.handler.FailureHandler;
import com.game.mancala.kalah.presentation.data.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.game.mancala.kalah.util.Constants.EMPTY_ACTIONS;
import static com.game.mancala.kalah.util.Constants.FAILURE_DURING_ACTIONS;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * The orchestrator that plays the actions.
 */
@Component
@Lazy
@Slf4j
public class GameOrchestrator implements Orchestrator<GameDTO> {

    @Resource private FailureHandler failureHandler;

    /**
     * The ovveridden play method which executes each {@link Action} that needs to be performed.
     * Handles the exceptions and store the data.
     * @param dto The input object that contains the necessary contextual information
     */
    @Override
    public void play(GameDTO dto) {
        final List<Action> actions = dto.getActionsToPerform();
        if(actions == null ||actions.isEmpty()) {
            throw new KalahException(GameOrchestrator.class.getName(),
                                     EMPTY_ACTIONS,
                                     INTERNAL_SERVER_ERROR.value(),
                                     INTERNAL_SERVER_ERROR);
        }
            for (Action action : actions) {
                Result result = (Result) action.perform(dto);
                failureHandler.handle(result, dto);        // Handle Failure
                if (dto.isStopProcessing()) {
                    log.warn("Stopping the execution of actions. Actions might not have been completed. {}", dto.getGameId());
                    throw new KalahException(FAILURE_DURING_ACTIONS,
                            INTERNAL_SERVER_ERROR.value(),
                            INTERNAL_SERVER_ERROR);
                }
            }
    }
}