package com.game.mancala.kalah.presentation.data;

import com.game.mancala.kalah.action.Action;
import com.game.mancala.kalah.model.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The DTO object that is used to transfer the contextual information necessary to play the game.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {

    private String gameId;

    private Integer pitId;

    private Game game;

    private boolean stopProcessing = false;

    private List<Action> actionsToPerform = new ArrayList<>();

    private Integer lastPit;

}
