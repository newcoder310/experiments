package com.game.mancala.kalah.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Game Entity that can contains the information of a game.
 */
@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    /**
     * The unique id of a game. The UUID
     */
    private String gameId;

    /**
     * The {@link Player} who is playing in context of the current game.
     */
    private Player currentPlayer;

    /**
     * The {@link Status} of the game.
     */
    private Status status;

    /**
     * The number of stones per per.
     */
    private Integer[] pitInformation;

    /**
     * The {@link Player} who won the game.
     */
    private Player winner;

}
