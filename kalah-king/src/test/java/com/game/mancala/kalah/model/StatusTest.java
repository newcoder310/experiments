package com.game.mancala.kalah.model;

import com.game.mancala.kalah.exceptions.KalahException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.game.mancala.kalah.model.Status.COMPLETED;
import static com.game.mancala.kalah.model.Status.IN_PROGRESS;
import static com.game.mancala.kalah.model.Status.isGameOver;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StatusTest {

    @Test
    public void isGameOverSuccess() {
        assertTrue(isGameOver(Game.builder().status(COMPLETED).build()));
    }

    @Test
    public void isGameOverFailure() {
        assertFalse(isGameOver(Game.builder().status(IN_PROGRESS).build()));
    }

    @Test(expected = KalahException.class)
    public void isGameOverException() {
        assertFalse(isGameOver(null));
    }
}