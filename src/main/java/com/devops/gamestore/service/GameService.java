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
     */
    public Game getGameById(final Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new game.
     *
     * @param game the game to create
     * @return the created game
     */
    public Game createGame(final Game game) {
        return gameRepository.save(game);
    }

    /**
     * Updates an existing game.
     *
     * @param id the ID of the game to update
     * @param game the updated game information
     * @return the updated game
     */
    public Game updateGame(final Long id, final Game game) {
        if (gameRepository.existsById(id)) {
            game.setId(id);
            return gameRepository.save(game);
        }
        return null;
    }

    /**
     * Deletes a game by its ID.
     *
     * @param id the ID of the game to delete
     */
    public void deleteGame(final Long id) {
        gameRepository.deleteById(id);
    }
}
