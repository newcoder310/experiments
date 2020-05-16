package com.game.mancala.kalah.action.postprocessing;

import com.game.mancala.kalah.presentation.data.GameDTO;
import com.game.mancala.kalah.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static com.game.mancala.kalah.action.Result.SUCCESS;
import static com.game.mancala.kalah.model.Player.PLAYER1;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetermineGameStatusActionTest {

    @InjectMocks
    private DetermineGameStatusAction fixture;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private GameDTO dtoMock;

    @Mock
    private Game game;

    private static final Integer[] IN_PROGRESS_PIT =  new Integer[]{6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
    private static final Integer[] COMPLETE_PIT = new Integer[]{0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 42};


    @Test
    public void testGameCompleteScenario() {
        ReflectionTestUtils.setField(fixture, "stonesPerPit", 6);
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        when(dtoMock.getGame().getPitInformation()).thenReturn(COMPLETE_PIT);
        assertEquals(SUCCESS, fixture.perform(dtoMock));
        verify(dtoMock.getGame(), never()).setCurrentPlayer(any());
        verify(dtoMock.getGame(), times(2)).setPitInformation(any());
    }

    @Test
    public void testGameNotCompleteScenario() {
        ReflectionTestUtils.setField(fixture, "stonesPerPit", 6);
        when(dtoMock.getGame().getCurrentPlayer()).thenReturn(PLAYER1);
        when(dtoMock.getGame().getPitInformation()).thenReturn(IN_PROGRESS_PIT);
        assertEquals(SUCCESS, fixture.perform(dtoMock));
        verify(dtoMock.getGame(), never()).setCurrentPlayer(any());
        verify(dtoMock.getGame(), never()).setPitInformation(any());
    }


}