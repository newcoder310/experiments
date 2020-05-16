package com.game.mancala.kalah.service;

import com.game.mancala.kalah.exceptions.KalahException;
import com.game.mancala.kalah.model.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(MockitoJUnitRunner.class)
public class GameCenterRepositoryImplTest {

    @InjectMocks
    private GameCenterRepositoryImpl fixture;

    @Mock
    private Game gameMock;

    @Test
    public void createGame() {
        ReflectionTestUtils.setField(fixture, "stones", 6);
        fixture.create();
    }

    @Test(expected = KalahException.class)
    public void createGameException() {
        ReflectionTestUtils.setField(fixture, "stones", -1);
        fixture.create();
    }

    @Test(expected = KalahException.class)
    public void getGameFailure() {
        fixture.get("xyz");
        fail();
    }
}