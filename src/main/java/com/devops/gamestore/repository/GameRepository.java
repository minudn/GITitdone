package com.devops.gamestore.repository;

import com.devops.gamestore.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Game entities.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    /**
     * Finds all games.
     *
     * @return a list of all games
     */
    List<Game> findAll();

    /**
     * Finds a game by its ID.
     *
     * @param id the ID of the game
     * @return the game with the specified ID
     */
    Game findById(Long id);

    // Other repository methods

}
