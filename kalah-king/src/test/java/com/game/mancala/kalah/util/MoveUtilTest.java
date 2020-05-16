package com.game.mancala.kalah.util;


import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.presentation.data.GameDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.game.mancala.kalah.model.Player.PLAYER1;
import static com.game.mancala.kalah.model.Player.PLAYER2;
import static com.game.mancala.kalah.util.MoveUtil.capture;
import static com.game.mancala.kalah.util.MoveUtil.distributeStones;
import static com.game.mancala.kalah.util.MoveUtil.emptyCurrentPit;
import static com.game.mancala.kalah.util.MoveUtil.moveToKalah;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoveUtilTest {

    @InjectMocks
    private MoveUtil fixture;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GameDTO dtoMock;

    private Integer[] PIT_INFORMATION = new Integer[]{2, 3, 6, 6, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6};;

    @Test
    public void emptyCurrentPitSuccess() {
        when(dtoMock.getPitId()).thenReturn(2);
        when(dtoMock.getGame().getPitInformation()).thenReturn(PIT_INFORMATION);
        assertEquals(Integer.valueOf(6), emptyCurrentPit(dtoMock));
    }

    @Test(expected = KalahException.class)
    public void emptyCurrentPitException() {
        emptyCurrentPit(null);
    }

    @Test
    public void distributeStonesSuccess() {
        when(dtoMock.getGame().getPitInformation()).thenReturn(PIT_INFORMATION);
        when(dtoMock.getPitId()).thenReturn(0);
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        assertEquals(Integer.valueOf(2), distributeStones(2,dtoMock));
        verify(dtoMock.getGame(), atMostOnce()).setPitInformation(any());
    }

    @Test
    public void distributeStonesCaptureSuccess() {
        when(dtoMock.getGame().getPitInformation()).thenReturn(PIT_INFORMATION);
        when(dtoMock.getPitId()).thenReturn(1);
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        assertEquals(Integer.valueOf(4), distributeStones(3,dtoMock));
        verify(dtoMock.getGame(), times(2)).setPitInformation(any());
    }

    @Test
    public void captureSuccess() {
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        when(dtoMock.getGame().getPitInformation()).thenReturn(PIT_INFORMATION);
        capture(Integer.valueOf(4), dtoMock);
        verify(dtoMock.getGame(), atMostOnce()).setPitInformation(any());
    }

    @Test
    public void captureFail() {
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        capture(Integer.valueOf(13), dtoMock);
        verify(dtoMock.getGame(), never()).setPitInformation(any());
    }

    @Test
    public void moveToKalahSuccess() {
        when(dtoMock.getGame().getPitInformation()).thenReturn(PIT_INFORMATION);
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        moveToKalah(dtoMock,10,PLAYER1.getKalah());
        verify(dtoMock.getGame(), atMostOnce()).setPitInformation(any());
    }

    @Test(expected = KalahException.class)
    public void moveToKalahFailure() {
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        moveToKalah(dtoMock,10,PLAYER2.getKalah());
        fail();
    }
}