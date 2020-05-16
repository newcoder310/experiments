package com.game.mancala.kalah.service;

/**
 * Game repository interface that could extends JPARespository or simply be used to store in an in memory database
 */
public interface GameRepository<T> {

    /**
     * Creates a new entity
     * @return The primary key of the entity
     */
    String create();

    /**
     *
     * @param gameId
     * @return The Entity of retrieved by the primary Key
     */
    T get(String gameId);

    /**
     * Updates the entity
     * @param game - The entity to be saved
     * @param gameId - The id of the entity to be saved
     */
    void update(T game, String gameId);

    /**
     * Deletes the entity by id
     * @param id Id of the entity to be deleted
     */
    void delete(String id);
}
