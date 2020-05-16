package com.game.mancala.kalah.presentation.resources;

import com.game.mancala.kalah.controller.GameController;
import com.game.mancala.kalah.model.Game;
import com.game.mancala.kalah.presentation.data.GameCompletionResponse;
import com.game.mancala.kalah.presentation.data.GameDTO;
import com.game.mancala.kalah.presentation.data.MoveResponse;
import com.game.mancala.kalah.presentation.data.NewGameResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.game.mancala.kalah.model.Status.COMPLETED;

/**
 * The Resource class that contains rest endpoints
 */
@RestController
@RequestMapping("/games")
@Slf4j
public class KalahResource {

    @Autowired
    private GameController controller;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewGameResponse createGame(HttpServletRequest request) {
        String gameId = controller.createGame();
        return new NewGameResponse(gameId, request.getRequestURL()+"/"+gameId);
    }

    @PutMapping("/{gameId}/pits/{pitId}")
    @ResponseStatus(HttpStatus.OK)
    public MoveResponse move(HttpServletRequest request,
                             @PathVariable("gameId") String gameId,
                             @PathVariable("pitId") String pitId) {
        controller.makeAMove(GameDTO.builder()
                            .gameId(gameId)
                            .pitId(Integer.valueOf(pitId)-1)
                            .build());
        String requestUrl = request.getRequestURL().toString();
        String trimmedUrl = requestUrl.substring(0, requestUrl.indexOf("/pits"));
        Game game = controller.getGame(gameId);
        Integer[] pitInformation = game.getPitInformation();
        if(COMPLETED == game.getStatus()) {
            return new GameCompletionResponse(gameId, trimmedUrl, pitInformation , game.getWinner(), game.getStatus());
        }
        return new MoveResponse(gameId,trimmedUrl,pitInformation);
    }

}
