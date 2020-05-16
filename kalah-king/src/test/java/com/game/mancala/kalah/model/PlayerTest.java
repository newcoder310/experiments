package com.game.mancala.kalah.model;

import com.game.mancala.kalah.exceptions.KalahException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static com.game.mancala.kalah.model.Player.PLAYER1;
import static com.game.mancala.kalah.model.Player.PLAYER2;
import static com.game.mancala.kalah.model.Player.isForbiddenPit;
import static com.game.mancala.kalah.model.Player.isKalah;
import static com.game.mancala.kalah.model.Player.isValidPit;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    @Test
    public void isValidPitTestSuccessCases() {
        Map<Player, Integer> pit = new HashMap<>();
        pit.put(PLAYER1, 0);
        pit.put(PLAYER1, 1);
        pit.put(PLAYER1, 2);
        pit.put(PLAYER1, 3);
        pit.put(PLAYER1, 4);
        pit.put(PLAYER1, 5);
        pit.put(PLAYER2, 7);
        pit.put(PLAYER2, 8);
        pit.put(PLAYER2, 9);
        pit.put(PLAYER2, 10);
        pit.put(PLAYER2, 11);
        pit.put(PLAYER2, 12);
        pit.forEach((k,v) -> assertTrue(isValidPit(k,v)));
    }

    @Test
    public void isValidPitTestFailureCases() {
        Map<Player, Integer> pit = new HashMap<>();
        pit.put(PLAYER2, 0);
        pit.put(PLAYER2, 1);
        pit.put(PLAYER2, 2);
        pit.put(PLAYER2, 3);
        pit.put(PLAYER2, 4);
        pit.put(PLAYER2, 5);
        pit.put(PLAYER1, 7);
        pit.put(PLAYER1, 8);
        pit.put(PLAYER1, 9);
        pit.put(PLAYER1, 10);
        pit.put(PLAYER1, 11);
        pit.put(PLAYER1, 12);
        pit.put(PLAYER1, 13);
        pit.put(PLAYER2, 13);
        pit.put(PLAYER1, 6);
        pit.put(PLAYER2, 6);
        pit.forEach((k,v) -> assertFalse(isValidPit(k,v)));
    }

    @Test
    public void isValidPitExceptionCase() {
        Map<Player, Integer> pit = new HashMap<>();
        pit.put(null, 0);
        pit.put(PLAYER2, -1);
        pit.forEach((k,v) -> {
            try {
                isValidPit(k, v);
                fail();
            } catch (KalahException exception) { } //expecting this exception. Do nothing
        });
    }



    @Test
    public void isKalahTestSuccessCases() {
        Map<Player, Integer> pit = new HashMap<>();
        pit.put(PLAYER2, 13);
        pit.put(PLAYER1, 6);
        pit.forEach((k,v) -> assertTrue(isKalah(k,v)));
    }

    @Test
    public void isValidKalahFailureCases() {
        Map<Player, Integer> pit = new HashMap<>();
        pit.put(PLAYER2, 0);
        pit.put(PLAYER2, 1);
        pit.put(PLAYER2, 2);
        pit.put(PLAYER2, 3);
        pit.put(PLAYER2, 4);
        pit.put(PLAYER2, 5);
        pit.put(PLAYER1, 7);
        pit.put(PLAYER1, 8);
        pit.put(PLAYER1, 9);
        pit.put(PLAYER1, 10);
        pit.put(PLAYER1, 11);
        pit.put(PLAYER1, 12);
        pit.put(PLAYER1, 0);
        pit.put(PLAYER1, 1);
        pit.put(PLAYER1, 2);
        pit.put(PLAYER1, 3);
        pit.put(PLAYER1, 4);
        pit.put(PLAYER1, 5);
        pit.put(PLAYER2, 7);
        pit.put(PLAYER2, 8);
        pit.put(PLAYER2, 9);
        pit.put(PLAYER2, 10);
        pit.put(PLAYER2, 11);
        pit.put(PLAYER2, 12);
        pit.put(PLAYER1, 13);
        pit.put(PLAYER2, 6);
        pit.forEach((k,v) -> assertFalse(isKalah(k,v)));
    }

    @Test
    public void isForbiddenPitSuccess() {
        Map<Player, Integer> pit = new HashMap<>();
        pit.put(PLAYER2, 6);
        pit.put(PLAYER1, 13);
        pit.forEach((k,v) -> assertTrue(isForbiddenPit(k,v)));
    }
    @Test
    public void isForbiddenPitFailure() {
        Map<Player, Integer> pit = new HashMap<>();
        pit.put(PLAYER2, 13);
        pit.put(PLAYER1, 6);
        pit.put(PLAYER2, 0);
        pit.put(PLAYER2, 1);
        pit.put(PLAYER2, 2);
        pit.put(PLAYER2, 3);
        pit.put(PLAYER2, 4);
        pit.put(PLAYER2, 5);
        pit.put(PLAYER1, 7);
        pit.put(PLAYER1, 8);
        pit.put(PLAYER1, 9);
        pit.put(PLAYER1, 10);
        pit.put(PLAYER1, 11);
        pit.put(PLAYER1, 12);
        pit.put(PLAYER1, 0);
        pit.put(PLAYER1, 1);
        pit.put(PLAYER1, 2);
        pit.put(PLAYER1, 3);
        pit.put(PLAYER1, 4);
        pit.put(PLAYER1, 5);
        pit.put(PLAYER2, 7);
        pit.put(PLAYER2, 8);
        pit.put(PLAYER2, 9);
        pit.put(PLAYER2, 10);
        pit.put(PLAYER2, 11);
        pit.put(PLAYER2, 12);
        pit.forEach((k,v) -> assertFalse(isForbiddenPit(k,v)));
    }

}