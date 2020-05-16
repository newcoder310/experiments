package com.game.mancala.kalah.action.postprocessing;

import com.game.mancala.kalah.presentation.data.GameDTO;
import com.game.mancala.kalah.model.Game;
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
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetermineWinnerActionTest {

    @InjectMocks
    private DetermineWinnerAction fixture;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GameDTO dtoMock;

    @Mock
    private Game gameMock;

    private static final Integer[] PLAYER1_PIT = new Integer[]{0, 0, 0, 0, 0, 0, 42, 0, 0, 0, 0, 0, 0, 30};
    private static final Integer[] PLAYER2_PIT = new Integer[]{0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 42};


    @Test
    public void testPlayerIWinScenario() {
        when(dtoMock.getGame().getPitInformation()).thenReturn(PLAYER1_PIT);
        assertEquals(SUCCESS, fixture.perform(dtoMock));
        verify(dtoMock.getGame(), atLeast(1)).setWinner(PLAYER1);
    }

    @Test
    public void testPlayer2WinScenario() {
        when(dtoMock.getGame().getPitInformation()).thenReturn(PLAYER2_PIT);
        assertEquals(SUCCESS, fixture.perform(dtoMock));
        verify(dtoMock.getGame(), atLeast(1)).setWinner(PLAYER2);
    }

}