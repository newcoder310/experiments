package com.game.mancala.kalah.action.move;

import com.game.mancala.kalah.action.Action;
import com.game.mancala.kalah.action.Result;
import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.presentation.data.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static com.game.mancala.kalah.util.Constants.STONES_MESSAGE;
import static com.game.mancala.kalah.util.MoveUtil.distributeStones;
import static com.game.mancala.kalah.util.MoveUtil.emptyCurrentPit;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Action class that deals with moving the stones
 */
@Component
@Lazy
@Slf4j
public class MoveStonesAction implements Action<GameDTO, Result> {

    /**
     * Action that only moves the stone moves the stones to the {@link GameDTO}.
     * It uses a few helper methods from the {@link com.game.mancala.kalah.util.MoveUtil} class
     *  1. Empties the selected pit.
     *  2. Distributes the stones from the pit
     * @param dto the object that contain the necessary values for performing the request
     * @return {@link Result#SUCCESS} on method execution
     */
    @Override
    public Result perform(GameDTO dto) {
        Integer stonesToDistribute = emptyCurrentPit(dto);
        if(stonesToDistribute <1) {
            throw new KalahException(MoveStonesAction.class.getName(),
                    STONES_MESSAGE,
                    INTERNAL_SERVER_ERROR.value(),
                    INTERNAL_SERVER_ERROR);
        }
        dto.setLastPit(distributeStones(
                stonesToDistribute,
                dto));
        return SUCCESS;
    }

}
