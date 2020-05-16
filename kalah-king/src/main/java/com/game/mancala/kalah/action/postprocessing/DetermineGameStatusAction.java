package com.game.mancala.kalah.action.postprocessing;

import com.game.mancala.kalah.action.Action;
import com.game.mancala.kalah.action.Result;
import com.game.mancala.kalah.model.Game;
import com.game.mancala.kalah.model.Player;
import com.game.mancala.kalah.presentation.data.GameDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.game.mancala.kalah.action.Result.FAILURE;
import static com.game.mancala.kalah.action.Result.SUCCESS;
import static com.game.mancala.kalah.model.Player.PLAYER1;
import static com.game.mancala.kalah.model.Player.PLAYER2;
import static com.game.mancala.kalah.model.Player.getOpponent;
import static com.game.mancala.kalah.model.Status.COMPLETED;

/**
 * Action that determines the current game status and which player to play next.
 */
@Component
@Lazy
@Slf4j
public class DetermineGameStatusAction implements Action<GameDTO, Result> {

    @Value("${kalah.stones}")
    private Integer stonesPerPit;

    /**
     *  Checks if the game is completed. If so,
     *    - Moves the remaining stones to the kalah's of the respective plaers
     *    - Tally's if the stones' in the kalah math the total stones
     *    - Changes the game status to {@link com.game.mancala.kalah.model.Status#COMPLETED}
     * @param dto the object that contain the necessary values for performing the request
     * @return {@link Result#SUCCESS} on evaluation
     */
    @Override
    public Result perform(GameDTO dto) {
        final Player currentPlayer = dto.getGame().getCurrentPlayer();

        if(isGameCompleted(dto)) {
            moveRemainingStonesToKalah(dto, currentPlayer);
            moveRemainingStonesToKalah(dto, getOpponent(currentPlayer));
            if(!tallyStones(dto.getGame().getPitInformation())){
               return FAILURE;
            }
            setGameStatus(dto);
        }

        return SUCCESS;
    }

    /**
     * Tally's if the number of stones combined in the Kalah's of both players is equal to to total number of stones.
     * @param pitInformation - Information about the pit and the stones.
     * @return true or false based on the tally.
     */
    private boolean tallyStones(Integer[] pitInformation) {
        return pitInformation[PLAYER1.getKalah()] + pitInformation[PLAYER2.getKalah()] == stonesPerPit*12;
    }

    /**
     * Moves the Remaining Stones of the player to their corresponding Kalah.
     * @param dto - The object that contains the necessary pit information
     * @param player - The player for whom the stones need to be moved
     */
    private void moveRemainingStonesToKalah(GameDTO dto, Player player) {
        Game game = dto.getGame();
        Integer[] pitInformation =  game.getPitInformation();
        int total = Arrays.stream(pitInformation, player.getStartingPit(), player.getKalah())
                .mapToInt(Integer::intValue)
                .sum();
        pitInformation[player.getKalah()]+=total;
        Arrays.fill(pitInformation,player.getStartingPit(),player.getKalah(), 0);
        dto.getGame().setPitInformation(pitInformation);
    }

    /**
     * Method to set the game satus to completed once the game is finished
     * @param dto - The object that contains the necessary information.
     */
    private void setGameStatus(GameDTO dto) {
        dto.getGame().setStatus(COMPLETED);
    }

    /**
     * Method that determines if the game is completed.
     * @param dto - The object that contains the necessary pit information
     * @return true of false based on evaluation
     */
    private boolean isGameCompleted(GameDTO dto) {
        Integer[] pitInformation = dto.getGame().getPitInformation();
        Player player = dto.getGame().getCurrentPlayer();

        return isSideEmpty(pitInformation, player) || isSideEmpty(pitInformation, getOpponent(player));
    }

    /**
     * Method to check of a side is empty
     * @param pitInformation - The pit information with the count of stones per pit
     * @param player - The current/opponent player whose pit's need to be empty
     * @return True if pit of a player is empty
     */
    private boolean isSideEmpty(Integer[] pitInformation, Player player) {
        return IntStream.range(player.getStartingPit(), player.getKalah())
                .allMatch(x -> pitInformation[x]==0);
    }
}
