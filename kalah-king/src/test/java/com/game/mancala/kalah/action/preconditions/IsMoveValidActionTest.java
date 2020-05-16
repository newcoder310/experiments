package com.game.mancala.kalah.action.preconditions;

import com.game.mancala.kalah.presentation.data.GameDTO;
import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static com.game.mancala.kalah.model.Player.PLAYER1;
import static com.game.mancala.kalah.model.Player.PLAYER2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IsMoveValidActionTest {

    @InjectMocks
    private IsMoveValidAction fixture;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GameDTO dtoMock;

    @Mock
    private Game gameMock;
    private Integer[] PIT_INFORMATION = new Integer[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};;
    private Integer[] EMPTY_PIT_INFORMATION = new Integer[]{6, 6, 6, 6, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6};;

    @Before
    public void setup() {
        when(dtoMock.getGame()).thenReturn(gameMock);
        when(dtoMock.getPitId()).thenReturn(5);
    }

    @Test
    public void testSuccess() {
        when(gameMock.getCurrentPlayer()).thenReturn(PLAYER1);
        when(gameMock.getPitInformation()).thenReturn(PIT_INFORMATION);
        assertEquals(SUCCESS, fixture.perform(dtoMock));
    }

    @Test(expected = KalahException.class)
    public void testEmptyPit() {
        when(gameMock.getCurrentPlayer()).thenReturn(PLAYER1);
        when(gameMock.getPitInformation()).thenReturn(EMPTY_PIT_INFORMATION);
        fixture.perform(dtoMock);
        fail();
    }

    @Test(expected = KalahException.class)
    public void testFailure() {
        when(gameMock.getCurrentPlayer()).thenReturn(PLAYER2);
        fixture.perform(dtoMock);
        fail();
    }

    @Test(expected = KalahException.class)
    public void testEmptyGame() {
        when(dtoMock.getGame()).thenReturn(null);
        fixture.perform(dtoMock);
        fail();
    }
}