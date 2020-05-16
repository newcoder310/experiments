package com.game.mancala.kalah.action.postprocessing;

import com.game.mancala.kalah.action.Action;
import com.game.mancala.kalah.action.Result;
import com.game.mancala.kalah.presentation.data.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static com.game.mancala.kalah.model.Player.PLAYER1;
import static com.game.mancala.kalah.model.Player.PLAYER2;

/**
 * Action to determine the winner
 */
@Component
@Lazy
@Slf4j
public class DetermineWinnerAction implements Action<GameDTO, Result> {

    /**
     * Sets the winner by comparing the number of stones in each Kalah.
     * @param dto - Object that contains the necessary information for the aciton.
     * @return {@link Result#SUCCESS} on a evaluation
     */
    @Override
    public Result perform(GameDTO dto) {

        Integer[] pitInformation = dto.getGame().getPitInformation();

        dto.getGame().setWinner(
                    pitInformation[PLAYER1.getKalah()] >= pitInformation[PLAYER2.getKalah()] ? PLAYER1
                                                                                              : PLAYER2);

        return SUCCESS;
    }
}
