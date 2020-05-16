package com.game.mancala.kalah.action.postprocessing;

import com.game.mancala.kalah.action.Action;
import com.game.mancala.kalah.action.Result;
import com.game.mancala.kalah.model.Player;
import com.game.mancala.kalah.presentation.data.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static com.game.mancala.kalah.model.Player.getOpponent;
import static com.game.mancala.kalah.model.Player.isKalah;
import static com.game.mancala.kalah.model.Status.IN_PROGRESS;
import static com.game.mancala.kalah.util.Constants.PLAYER_CHANGED;

/**
 * Class that determines the next player.
 */
@Component
@Lazy
@Slf4j
public class DetermineNextPlayerAction implements Action<GameDTO, Result> {

    /**
     * Method Determines if the last pit is the Kalah of the current player. Ohterwise,
     *  switches the player
     * @param dto The input object that contains the necessary contextual information
     * @return - SUCCESS on completion of the action
     */
    @Override
    public Result perform(GameDTO dto) {
        Integer lastPit = dto.getLastPit();
        final Player currentPlayer = dto.getGame().getCurrentPlayer();


        if(!isKalah(currentPlayer, lastPit) && (IN_PROGRESS == dto.getGame().getStatus())) {
            log.debug(PLAYER_CHANGED);
            dto.getGame().setCurrentPlayer(getOpponent(currentPlayer));
        }

        return SUCCESS;

    }
}
