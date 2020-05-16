package com.game.mancala.kalah.controller;

import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.model.Game;
import com.game.mancala.kalah.presentation.data.GameDTO;
import com.game.mancala.kalah.service.GameRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

    @InjectMocks
    private GameController fixture;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GameDTO dtoMock;

    @Mock
    private GameRepository gameCenterRepositoryImplMock;

    @Mock
    private GameActions gameActionsMock;

    @Mock
    private Orchestrator orchestratorMock;

    @Mock
    private Game gameMock;

    @Test
    public void makeMove() {
        when(gameCenterRepositoryImplMock.get(any())).thenReturn(gameMock);
        fixture.makeAMove(dtoMock);
        verify(dtoMock, times(1)).getGameId();
        verify(orchestratorMock, atMostOnce()).play(any());
        verify(dtoMock, atMostOnce()).setActionsToPerform(any());
        verify(gameActionsMock, atMostOnce()).getActionsToPerform();
    }

    @Test(expected = KalahException.class)
    public void makeMoveNoGameCase() {
        when(gameCenterRepositoryImplMock.get(any())).thenThrow(KalahException.class);
        fixture.makeAMove(dtoMock);
        fail();
    }

    @Test(expected = KalahException.class)
    public void makeMoveKalahExceptionFromPlayCase() {
        when(gameCenterRepositoryImplMock.get(any())).thenReturn(gameMock);
        doAnswer( x -> {
            throw new KalahException();
        }).when(orchestratorMock).play(any());
        fixture.makeAMove(dtoMock);
        fail();
    }

    @Test(expected = Exception.class)
    public void makeMoveExceptionFromPlayCase() {
        when(gameCenterRepositoryImplMock.get(any())).thenReturn(gameMock);
        doAnswer( x -> {
            throw new Exception();
        }).when(orchestratorMock).play(any());
        fixture.makeAMove(dtoMock);
    }


}