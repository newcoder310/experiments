package com.game.mancala.kalah.action.preconditions;

import com.game.mancala.kalah.presentation.data.GameDTO;
import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.model.Game;
import com.game.mancala.kalah.model.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IsGameValidActionTest {

    @InjectMocks
    private IsGameValidAction fixture;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GameDTO dtoMock;

    @Mock
    private Game gameMock;

    @Test
    public void testSuccess() {
        when(dtoMock.getGame()).thenReturn(gameMock);
        when(gameMock.getStatus()).thenReturn(Status.IN_PROGRESS);
        assertEquals(SUCCESS, fixture.perform(dtoMock));
    }

    @Test(expected = KalahException.class)
    public void testFailure() {
        when(dtoMock.getGame()).thenReturn(gameMock);
        when(gameMock.getStatus()).thenReturn(Status.COMPLETED);
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