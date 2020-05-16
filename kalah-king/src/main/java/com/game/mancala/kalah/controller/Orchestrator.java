package com.game.mancala.kalah.controller;

/**
 * The custom orchestrator interface to trigger the play of the game.
 * @param <INPUT> Generic placeholder for the input
 */
public interface Orchestrator<INPUT> {

    void play(INPUT input);
}
