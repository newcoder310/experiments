package com.game.mancala.kalah.action;

import lombok.Getter;

/**
 * Class that contains the {@link Result}  of an {@link Action}
 */
@Getter
public enum Result {
    SUCCESS, FAILURE;

    private Result result;

    public boolean isFailure() {
        return result == Result.FAILURE;
    }

    public boolean isSuccess() {
        return result == Result.SUCCESS;
    }
}


