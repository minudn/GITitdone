package com.devops.gamestore.repository;

import com.devops.gamestore.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Game entities.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    /**
     * Finds games by name containing the specified string, ignoring case.
     *
     * @param name the string to search for in game names
     * @return a list of games with names containing the specified string
     */
    List<Game> findByNameContainingIgnoreCase(String name);

}