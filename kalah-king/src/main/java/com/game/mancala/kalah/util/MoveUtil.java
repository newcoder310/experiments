package com.game.mancala.kalah.util;

import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.model.Player;
import com.game.mancala.kalah.presentation.data.GameDTO;

import static com.game.mancala.kalah.model.Player.PLAYER2;
import static com.game.mancala.kalah.model.Player.isForbiddenPit;
import static com.game.mancala.kalah.model.Player.isKalah;
import static com.game.mancala.kalah.model.Player.isValidPit;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Utility class for different actions to be performed in the game.
 */
public class MoveUtil {

    /**
     * Empties the currentpit for the selected pit
     * @param dto - The object that contains the necessary input
     * @return - The stones that have been taken from the pit and need to be distributed across.
     */
    public static Integer emptyCurrentPit(GameDTO dto) {
        if(dto == null) {
            throw new KalahException(MoveUtil.class.getName(),
                                     "DTO cannot be null, Try again!!",
                                     INTERNAL_SERVER_ERROR.value(),
                                     INTERNAL_SERVER_ERROR);
        }
        return emptyCurrentPit(dto, dto.getPitId());
    }

    /**
     * Empties the current pit based on the pitId.
     * @param dto - The object that contains the necessary input
     * @param pitId -The pit Id that
     * @return - The stones that have been taken from the pit and need to be distributed across.
     */
    private static Integer emptyCurrentPit(GameDTO dto, Integer pitId) {
        final Integer[] pitInformation = dto.getGame().getPitInformation();
        final Integer stonesToMove = pitInformation[pitId];
        pitInformation[pitId]=0;
        return stonesToMove;
    }

    /**
     * Distributes the stones empties from a particular pit.
     * @param stonesToMove - The stones that need to be distributed across.
     * @param dto - The input that contains the necessary contextual information
     * @return - Returns the id of the last pit.
     */
    public static Integer distributeStones(Integer stonesToMove, GameDTO dto) {
        Integer pitId = dto.getPitId();
        Integer[] pitInformation = dto.getGame().getPitInformation();
        pitId++;
        do {
            if(pitId>PLAYER2.getKalah()) {
                pitId = 0;
            }
            if(!isForbiddenPit(dto.getGame().getCurrentPlayer(), pitId)) {
                pitInformation[pitId]++;
                stonesToMove--;
            }
            pitId++;
        } while (stonesToMove !=0);

        dto.getGame().setPitInformation(pitInformation);

        Integer lastpit = --pitId;
        if(pitInformation[lastpit] == 1) {
            capture(lastpit, dto);
        }
        return lastpit;
    }

    /**
     * Method to capture the stones of the opponent. Logic works as follows.
     *  - As there are 13 indices for 14 pits in total with 6 on each side,
     *    the opponents pit can be identified by subtracting the last valid index from the current index
     *     Eg: 12-12 = 0 i.e 0th index is the opposite of the 12th index
     *         Similarly, 12 - 11  =  1
     *                    12 - 10  =  2
     *                    12 - 9   =  3
     *                    12 - 8   =  4
     *                    12 - 7   =  5
     *                    12 - 5   =  7
     *                    12 - 4   =  8
     *                    12 - 3   =  9
     *                    12 - 2   =  10
     *                    12 - 1   =  11
     *                    12 - 0   =  12
     * If the last pit is Valid means that it is the pit of the player and is not the kalah of the player, only then
     * will the stones of the opponent be moved to the current players Kalah
     * @param lastPit - Index of the pit where the game
     * @param dto
     */
    public static void capture(Integer lastPit, GameDTO dto) {
        Player currentPlayer = dto.getGame().getCurrentPlayer();
        if(isValidPit(currentPlayer, lastPit)) {
            Integer stonesToMove = emptyCurrentPit(dto, PLAYER2.getEndingPit()-lastPit);
            stonesToMove+=emptyCurrentPit(dto,lastPit);
            moveToKalah(dto, stonesToMove, currentPlayer.getKalah());
        }
    }

    /**
     * Method to move the stones to kalah.
     * @param dto - Input that contains the necessary contextual information of game
     * @param stones - The number of stones to move
     * @param kalah - The index of the kalah to which the stones have to be moved to.
     */
    public static void moveToKalah(GameDTO dto, Integer stones, Integer kalah) {
        if (!isKalah(dto.getGame().getCurrentPlayer(), kalah)) {
            throw new KalahException(MoveUtil.class.getName(),
                    String.format("Not the correct kalah. Try again! Player %s, Kalah %s",
                            dto.getGame().getCurrentPlayer(), kalah),
                            INTERNAL_SERVER_ERROR.value(),
                            INTERNAL_SERVER_ERROR);
        }
        Integer[] pitInformation = dto.getGame().getPitInformation();
        pitInformation[kalah]+=stones;
        dto.getGame().setPitInformation(pitInformation);
    }
}
