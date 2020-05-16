package com.game.mancala.kalah.action;

/**
 * Interface that defines the behaviour of the Action
 * @param <INPUT> - Generic for the input type of the Action
 * @param <OUTPUT> - Generic for the output type of the Action
 */
public interface Action<INPUT, OUTPUT> {
    OUTPUT perform(INPUT input);
}
