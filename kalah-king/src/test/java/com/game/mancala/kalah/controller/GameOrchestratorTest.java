package com.game.mancala.kalah.controller;

import com.game.mancala.kalah.action.preconditions.IsGameValidAction;
import com.game.mancala.kalah.presentation.data.GameDTO;
import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.handler.FailureHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameOrchestratorTest {

    @InjectMocks
    private GameOrchestrator fixture;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GameDTO dtoMock;

    @Mock
    private IsGameValidAction actionMock;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private FailureHandler failureHandler;

    @Test
    public void testPlaySuccess() {
        when(dtoMock.getActionsToPerform()).thenReturn(Arrays.asList(actionMock));
        when(actionMock.perform(any())).thenReturn(SUCCESS);
        fixture.play(dtoMock);
        verify(dtoMock, never()).setStopProcessing(anyBoolean());
    }

    @Test(expected = KalahException.class)
    public void testPlayFailure() {
        when(dtoMock.getActionsToPerform()).thenReturn(Arrays.asList(actionMock));
        when(actionMock.perform(any())).thenReturn(SUCCESS);
        when(dtoMock.isStopProcessing()).thenReturn(true);
        fixture.play(dtoMock);
        verify(dtoMock, atMostOnce()).setStopProcessing(anyBoolean());
    }

    @Test(expected = KalahException.class)
    public void testEmptyActionsFailure() {
        when(dtoMock.getActionsToPerform()).thenReturn(Arrays.asList());
        fixture.play(dtoMock);
        fail();
    }

    @Test(expected = KalahException.class)
    public void testNullActionsFailure() {
        when(dtoMock.getActionsToPerform()).thenReturn(null);
        fixture.play(dtoMock);
        fail();
    }


}