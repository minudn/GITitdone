package com.devops.gamestore.controller;

import com.devops.gamestore.entity.Game;
import com.devops.gamestore.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling game-related requests.
 */
@RestController
@RequestMapping("/games")
public class GameController {

    /**
     * Service for managing games.
     */
    @Autowired
    private GameService gameService;

    /**
     * Retrieves a list of all games.
     *
     * @return a list of all games
     */
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    /**
     * Retrieves a game by its ID.
     *
     * @param id the ID of the game
     * @return the game with the specified ID
     */
    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return gameService.getGameById(id);
    }

    /**
     * Creates a new game.
     *
     * @param game the game to create
     * @return the created game
     */
    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    /**
     * Updates an existing game.
     *
     * @param id the ID of the game to update
     * @param game the updated game information
     * @return the updated game
     */
    @PutMapping("/{id}")
    public Game updateGame(@PathVariable Long id, @RequestBody Game game) {
        return gameService.updateGame(id, game);
    }

    /**
     * Deletes a game by its ID.
     *
     * @param id the ID of the game to delete
     */
    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
    }
}