package com.game.mancala.kalah.action.postprocessing;

import com.game.mancala.kalah.model.Game;
import com.game.mancala.kalah.presentation.data.GameDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static com.game.mancala.kalah.model.Player.PLAYER1;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetermineNextPlayerActionTest {


    @InjectMocks
    private DetermineNextPlayerAction fixture;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GameDTO dtoMock;

    @Mock
    private Game game;

    @Test
    public void testLastIndexIsKalaScenario() {
        when(dtoMock.getLastPit()).thenReturn(6);
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        assertEquals(SUCCESS, fixture.perform(dtoMock));
        verify(dtoMock.getGame(), never()).setCurrentPlayer(any());
        verify(dtoMock.getGame(), never()).setPitInformation(any());
    }

    @Test
    public void testLastIndexNotKalaScenario() {
        when(dtoMock.getLastPit()).thenReturn(12);
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        assertEquals(SUCCESS, fixture.perform(dtoMock));
        verify(dtoMock.getGame(), atMostOnce()).setCurrentPlayer(any());
        verify(dtoMock.getGame(), never()).setPitInformation(any());
    }
}