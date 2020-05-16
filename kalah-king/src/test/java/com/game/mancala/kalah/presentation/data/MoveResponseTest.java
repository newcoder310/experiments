package com.game.mancala.kalah.presentation.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class MoveResponseTest {

    private Integer[] EMPTY_PIT_INFORMATION = new Integer[]{6, 6, 6, 6, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6};;

    @Test
    public void testMoveResponse() {
        MoveResponse moveResponse = new MoveResponse("testId", "test", EMPTY_PIT_INFORMATION);
        Map<Integer, Integer> status = moveResponse.getStatus();
        assertEquals(status.keySet().size(), EMPTY_PIT_INFORMATION.length);
    }

}