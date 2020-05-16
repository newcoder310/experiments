package com.game.mancala.kalah.controller;

import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.model.Game;
import com.game.mancala.kalah.presentation.data.GameDTO;
import com.game.mancala.kalah.service.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Controller class that dictates the course of actions that need to be taken to make a move.
 */
@Component
@Lazy
public class GameController {
    @Autowired
    private GameRepository<Game> gameCenterRepositoryImpl;

    @Autowired
    private GameActions gameActions;

    @Autowired
    private Orchestrator<GameDTO> orchestrator;

    /**
     * Method that performs the necessary steps to make a move
     *  1. Gets the current game.
     *  2. Gets the actions that need to be  performed.
     * @param dto - The object that contains the necessary contextual information to play.
     * @throws KalahException is thrown if there is an error during the play
     */
    public void makeAMove(GameDTO dto) {
        dto.setGame(getGame(dto.getGameId()));
        dto.setActionsToPerform(gameActions.getActionsToPerform());
        orchestrator.play(dto);
    }

    /**
     * Method that calls the {@link GameRepository} to create a new {@link Game}
     * @return The id of the created game
     */
    public String createGame() {
        return gameCenterRepositoryImpl.create();
    }

    /**
     * Method that calls the {@link GameRepository} to get a {@link Game} by gameId
     * @param gameId The id of the game
     * @return {@link Game} that is returned for the id
     * @throws KalahException is thrown when there is not game found
     */
    public Game getGame(String gameId) {
        return gameCenterRepositoryImpl.get(gameId);
    }


}
