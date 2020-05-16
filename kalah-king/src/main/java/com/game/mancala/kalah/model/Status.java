package com.game.mancala.kalah.model;

import com.game.mancala.kalah.exceptions.KalahException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Enum that contains the Status of the game
 */
public enum Status {
    IN_PROGRESS,
    COMPLETED;

    /**
     * Method to check if the game is completed based on the status of {@link Game}.
     * @param game - The Game for which the status needs to be chehcked
     * @return True or false based on evaluation
     */
    public static boolean isGameOver(Game game) {
        if(game == null) {
            throw new KalahException(Status.class.getName(),
                                    "Game Cannot be empty. Try Again!!",
                                    INTERNAL_SERVER_ERROR.value(),
                                    INTERNAL_SERVER_ERROR);
        }
        return game.getStatus() == COMPLETED;
    }
}
