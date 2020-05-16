package com.game.mancala.kalah.controller;

import com.game.mancala.kalah.action.Action;
import com.game.mancala.kalah.action.move.MoveStonesAction;
import com.game.mancala.kalah.action.postprocessing.DetermineGameStatusAction;
import com.game.mancala.kalah.action.postprocessing.DetermineNextPlayerAction;
import com.game.mancala.kalah.action.postprocessing.DetermineWinnerAction;
import com.game.mancala.kalah.action.preconditions.IsGameValidAction;
import com.game.mancala.kalah.action.preconditions.IsMoveValidAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that get the list of actions that need to be performed.
 */
@Component
@Lazy
public class GameActions {

    @Autowired
    private IsGameValidAction isGameValidAction;
    @Autowired
    private IsMoveValidAction isMoveValidAction;
    @Autowired
    private MoveStonesAction moveStonesAction;
    @Autowired
    private DetermineGameStatusAction determineGameStatusAction;
    @Autowired
    private DetermineWinnerAction determineWinnerAction;
    @Autowired
    private DetermineNextPlayerAction determineNextPlayerAction;

    /**
     * Loads the list of actions that are needed for playing.
     * @return List of Actions that need to be executed
     */
    public List<Action> getActionsToPerform() {
        List<Action> actionsToPerform = new LinkedList<>();
        actionsToPerform.add(isGameValidAction);
        actionsToPerform.add(isMoveValidAction);
        actionsToPerform.add(moveStonesAction);
        actionsToPerform.add(determineGameStatusAction);
        actionsToPerform.add(determineNextPlayerAction);
        actionsToPerform.add(determineWinnerAction);
        return actionsToPerform;
    }
}
