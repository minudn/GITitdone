package com.devops.gamestore.service;

import com.devops.gamestore.entity.Game;
import com.devops.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing games.
 */
@Service
public class GameService {

    /**
     * Repository for accessing game data.
     */
    @Autowired
    private GameRepository gameRepository;

    /**
     * Retrieves a list of all games.
     *
     * @return a list of all games
     */
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    /**
     * Retrieves a game by its ID.
     *
     * @param id the ID of the game
     * @return the game with the specified ID
     * @throws RuntimeException if the game is not found
     */
    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
    }

    /**
     * Creates a new game.
     *
     * @param game the game to create
     * @return the created game
     */
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    /**
     * Updates an existing game.
     *
     * @param id the ID of the game to update
     * @param updatedGame the updated game information
     * @return the updated game
     */
    public Game updateGame(Long id, Game updatedGame) {
        Game existingGame = getGameById(id);
        existingGame.setName(updatedGame.getName());
        existingGame.setDeveloper(updatedGame.getDeveloper());
        existingGame.setGenre(updatedGame.getGenre());
        existingGame.setPrice(updatedGame.getPrice());
        return gameRepository.save(existingGame);
    }

    /**
     * Deletes a game by its ID.
     *
     * @param id the ID of the game to delete
     */
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}