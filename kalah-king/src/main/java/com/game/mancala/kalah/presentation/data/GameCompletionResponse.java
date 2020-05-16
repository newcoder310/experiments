package com.game.mancala.kalah.presentation.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.game.mancala.kalah.model.Player;
import com.game.mancala.kalah.model.Status;
import lombok.Getter;

/**
 * Response class that builds the response.
 */
@Getter
@JsonInclude()
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameCompletionResponse extends MoveResponse {

    /**
     * Winner of the game
     */
    private Player winner;

    /**
     * Status of the game
     */
    private Status gameStatus;

    public GameCompletionResponse(String id, String url, Integer[] pitInformation, Player winner, Status gameStatus) {
        super(id, url, pitInformation);
        this.winner = winner;
        this.gameStatus = gameStatus;
    }
}
