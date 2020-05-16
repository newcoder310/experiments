package com.game.mancala.kalah.action.preconditions;

import com.game.mancala.kalah.action.Action;
import com.game.mancala.kalah.action.Result;
import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.model.Game;
import com.game.mancala.kalah.model.Player;
import com.game.mancala.kalah.presentation.data.GameDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static com.game.mancala.kalah.model.Player.isValidPit;
import static com.game.mancala.kalah.util.Constants.GAME_CANNOT_BE_EMPTY;
import static com.game.mancala.kalah.util.Constants.INVALID_PIT;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Component
@Lazy
public class IsMoveValidAction implements Action<GameDTO, Result> {

    @Override
    public Result perform(GameDTO gameDTO) {
        final Game game = gameDTO.getGame();
        if (game == null) {
            throw new KalahException(IsMoveValidAction.class.getName(),
                                     GAME_CANNOT_BE_EMPTY,
                                     INTERNAL_SERVER_ERROR.value(),
                                     INTERNAL_SERVER_ERROR);
        }

        final Player currentPlayer = game.getCurrentPlayer();
        final Integer pitId = gameDTO.getPitId();

        if(!isValidPit(currentPlayer, pitId)) {
            throw new KalahException(IsMoveValidAction.class.getName(),
                                    INVALID_PIT,
                                     NOT_FOUND.value(),
                                     NOT_FOUND);
        }

        if(isPitEmpty(game.getPitInformation(), pitId)) {
            throw new KalahException(IsMoveValidAction.class.getName(),
                                     INVALID_PIT,
                                     BAD_REQUEST.value(),
                                     BAD_REQUEST);
        }

        return SUCCESS;
    }

    private boolean isPitEmpty(Integer[] pitInformation, Integer pitId) {
        return pitInformation[pitId] <= 0;
    }
}
