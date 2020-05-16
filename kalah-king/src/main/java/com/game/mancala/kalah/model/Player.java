package com.game.mancala.kalah.model;

import com.game.mancala.kalah.exceptions.KalahException;
import lombok.Getter;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Enum that contains the Player information in the game
 */
public enum Player {
    PLAYER1(0,5, 6, 13),
    PLAYER2(7,12, 13, 6);

    @Getter
    private Integer startingPit;
    @Getter
    private Integer endingPit;
    @Getter
    private Integer kalah;
    @Getter
    private Integer forbiddenPit;

    Player(Integer startingPit, Integer endingPit, Integer kalah, Integer forbiddenPit) {
        this.startingPit = startingPit;
        this.endingPit = endingPit;
        this.kalah = kalah;
        this.forbiddenPit = forbiddenPit;
    }

    /**
     * Method to check if the selected pit is valid or not by checking if it is in range of the {@link Player}
     * in context
     * @param player - The player in context
     * @param pitId - The pitId that needs to be checked
     * @return True or False based on evaluation
     */
    public static boolean isValidPit(Player player, Integer pitId) {
        validateFields(player,pitId);
        return pitId >= player.getStartingPit() && pitId <= player.getEndingPit();
    }

    /**
     * Method to check if the selected pit is the kalah or not for the {@link Player}
     * in context
     * @param player - The player in context
     * @param pitId - The pitId that needs to be checked
     * @return True or False based on evaluation
     */
    public static boolean isKalah(Player player, Integer pitId) {
        validateFields(player,pitId);
        return pitId == player.getKalah();
    }

    /**
     * Method to check if the selected pit is a forbidden kalah for the  for the {@link Player}
     * in context.
     *  - Pit 13 is forbidden for {@link Player#PLAYER1}
     *  - Pit 6 is forbidden for {@link Player#PLAYER2}
     * @param player - The player in context
     * @param pitId - The pitId that needs to be checked
     * @return True or False based on evaluation
     */
    public static boolean isForbiddenPit(Player player, Integer pitId) {
        validateFields(player,pitId);
        return pitId == player.getForbiddenPit();
    }

    /**
     * Return the opponent of the {@link Player} in context
     * @param player - The player in context
     * @return The opposing {@link Player}
     */
    public static Player getOpponent(Player player) {
        if(player == null) {
            throw new KalahException(Player.class.getName(),
                    String.format("Invalid information player %s",player),
                    INTERNAL_SERVER_ERROR.value(),
                    INTERNAL_SERVER_ERROR);
        }
        return PLAYER1 == player ? PLAYER2 : PLAYER1;
    }


    private static void validateFields(Player player, Integer pitId) {
        if(player == null || pitId <0) {
            throw new KalahException(Player.class.getName(),
                    String.format("Invalid information player %s, pitId %s ",player, pitId),
                    INTERNAL_SERVER_ERROR.value(),
                    INTERNAL_SERVER_ERROR);
        }
    }
}
