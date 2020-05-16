package com.game.mancala.kalah.controller;

import com.game.mancala.kalah.action.move.MoveStonesAction;
import com.game.mancala.kalah.action.postprocessing.DetermineGameStatusAction;
import com.game.mancala.kalah.action.postprocessing.DetermineNextPlayerAction;
import com.game.mancala.kalah.action.postprocessing.DetermineWinnerAction;
import com.game.mancala.kalah.action.preconditions.IsGameValidAction;
import com.game.mancala.kalah.action.preconditions.IsMoveValidAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GameActionsTest {

    @InjectMocks
    private GameActions fixture;
    @Mock
    private IsGameValidAction isGameValidAction;
    @Mock
    private IsMoveValidAction isMoveValidAction;
    @Mock
    private MoveStonesAction moveStonesAction;
    @Mock
    private DetermineGameStatusAction determineGameStatusAction;
    @Mock
    private DetermineNextPlayerAction determineNextPlayerAction;
    @Mock
    private DetermineWinnerAction determineWinnerAction;

    @Test
    public void getActionsToPerform() {
        assertEquals(6, fixture.getActionsToPerform().size());
    }
}