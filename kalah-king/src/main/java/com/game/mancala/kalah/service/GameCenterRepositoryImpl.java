package com.game.mancala.kalah.service;

import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.game.mancala.kalah.model.Player.PLAYER1;
import static com.game.mancala.kalah.model.Player.PLAYER2;
import static com.game.mancala.kalah.model.Status.IN_PROGRESS;
import static com.game.mancala.kalah.util.Constants.GAME_CREATION_FAIL;
import static com.game.mancala.kalah.util.Constants.GAME_NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * The singleton bean that contains all the {@link Game} created. Implements the {@link GameRepository}
 */
@Service
@Scope("singleton")
@Slf4j
public class GameCenterRepositoryImpl implements GameRepository<Game> {

    /**
     * A map for the {@link Game}
     */
    private Map<String, Game> games = new ConcurrentHashMap<>();

    /**
     * Default number of stones in the KALAH of each player
     */
    private static final Integer STONES_IN_KALAH = 0;

    /**
     * The number of stones which each pit needs to be initialized. Injected by the application
     */
    @Value("${kalah.stones}")
    private Integer stones;

    /**
     * Creates a new {@link Game} and return the gameId
     * @return
     */
    public String create() {
        log.info("Creating a new game");
        String gameId =  UUID.randomUUID().toString();
        Integer[] pitInformation = new Integer[14];
        if(stones < 1) {
            log.error("Game cannot be create as stone count is not valid {} ", stones);
            throw  new KalahException(GameCenterRepositoryImpl.class.getName(),
                                      String.format(GAME_CREATION_FAIL, stones),
                                      INTERNAL_SERVER_ERROR.value(),
                                      INTERNAL_SERVER_ERROR);
        }
        Arrays.fill(pitInformation,PLAYER1.getStartingPit(),PLAYER1.getKalah(),stones);
        Arrays.fill(pitInformation,PLAYER2.getStartingPit(),PLAYER2.getKalah(),stones);
        Arrays.fill(pitInformation,PLAYER1.getKalah(),PLAYER1.getKalah()+1,STONES_IN_KALAH);
        Arrays.fill(pitInformation,PLAYER2.getKalah(),PLAYER2.getKalah()+1,STONES_IN_KALAH);
        games.put(gameId, Game.builder()
                      .gameId(gameId)
                      .currentPlayer(PLAYER1)
                      .pitInformation(pitInformation)
                      .status(IN_PROGRESS)
                      .build());
        log.info("New Game with id {}, has been created",gameId);
        return gameId;
    }

    /**
     * Retruns a {@link Game} based on the gameId
     * @param gameId The id of the game.
     * @return - Returns the {@link Game}
     * @throws KalahException is thrown if a game with the id is not found
     */
    public Game get(String gameId) {
        return games.computeIfAbsent(gameId, k -> {
            throw new KalahException(GameCenterRepositoryImpl.class.getName(),
                                     String.format(GAME_NOT_FOUND, k),
                                     NOT_FOUND.value(),
                                     NOT_FOUND);
        });
    }

    /**
     * Deletes a game by ID
     */
    public void delete(String id) {
        games.remove(id);
    }

    /**
     * Updates the current game after the move is completed.
     * @param game - The temporary game object that
     */
    public void update(Game game, String gameId) {
        if(gameId.equals(game.getGameId())) {
            games.put(game.getGameId(), game);
        }
    }

}


