package com.game.mancala.kalah.action.preconditions;

import com.game.mancala.kalah.action.Action;
import com.game.mancala.kalah.action.Result;
import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.model.Game;
import com.game.mancala.kalah.presentation.data.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static com.game.mancala.kalah.model.Status.isGameOver;
import static com.game.mancala.kalah.util.Constants.GAME_OVER;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Actions that checks if a game is valid.
 */
@Component
@Lazy
@Slf4j
public class IsGameValidAction implements Action<GameDTO, Result> {

    /**
     * Method to check if a {@link Game} is valid.
     * - Checks if the {@link Game} instance is empty
     *  - Calls the {@link com.game.mancala.kalah.model.Status#isGameOver(Game)}
     * @param dto The object that contains the necessary contextual information
     * @return Success or Failure based on evaluation
     */
    @Override
    public Result perform(GameDTO dto) {
        final Game game = dto.getGame();
        if(game == null) {
            throw new KalahException(IsGameValidAction.class.getName(),
                                    INTERNAL_SERVER_ERROR.value(),
                                    INTERNAL_SERVER_ERROR);
        }
        if (isGameOver(game)) {
            throw new KalahException(IsGameValidAction.class.getName(),
                    String.format(GAME_OVER, game.getWinner()),
                    BAD_REQUEST.value(),
                    BAD_REQUEST);
        }
        return SUCCESS;
    }
}
