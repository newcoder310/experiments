package com.game.mancala.kalah.action.move;

import com.game.mancala.kalah.presentation.data.GameDTO;
import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static com.game.mancala.kalah.model.Player.PLAYER1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoveStonesActionTest {

    private static final Integer[] IN_PROGRESS_PIT =  new Integer[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
    private static final Integer[] CAPTURE_PIT =  new Integer[]{6, 2, 6, 0, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
    private static final Integer[] EMPTY_PIT =  new Integer[]{0, 2, 6, 0, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};

    @InjectMocks
    private MoveStonesAction fixture;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GameDTO dtoMock;

    @Mock
    private Game gameMock;

    @Test
    public void testSuccessNoCapture() {
        when(dtoMock.getPitId()).thenReturn(0);
        when(dtoMock.getGame()).thenReturn(gameMock);
        when(dtoMock.getGame().getPitInformation()).thenReturn(IN_PROGRESS_PIT);
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        assertEquals(SUCCESS, fixture.perform(dtoMock));
        verify(dtoMock, atMostOnce()).setLastPit(anyInt());
        verify(dtoMock.getGame(), atMostOnce()).setPitInformation(any());
    }

    @Test
    public void testSuccessCapture() {
        when(dtoMock.getPitId()).thenReturn(1);
        when(dtoMock.getGame()).thenReturn(gameMock);
        when(dtoMock.getGame().getPitInformation()).thenReturn(CAPTURE_PIT);
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        assertEquals(SUCCESS, fixture.perform(dtoMock));
        verify(dtoMock, atMostOnce()).setLastPit(anyInt());
        verify(dtoMock.getGame(), times(2)).setPitInformation(any());
    }

    @Test(expected = KalahException.class)
    public void testFailure() {
        when(dtoMock.getPitId()).thenReturn(0);
        when(dtoMock.getGame()).thenReturn(gameMock);
        when(dtoMock.getGame().getPitInformation()).thenReturn(EMPTY_PIT);
        fixture.perform(dtoMock);
        fail();
    }

}