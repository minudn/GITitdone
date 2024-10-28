package com.devops.gamestore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a game entity in the Game Store application.
 */
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the game.
     */
    private String name;

    /**
     * The developer of the game.
     */
    private String developer;

    /**
     * The genre of the game.
     */
    private String genre;

    /**
     * The price of the game.
     */
    private Double price;

    /**
     * Constructs a new Game with the specified details.
     *
     * @param gameId the ID of the game
     * @param gameName the name of the game
     * @param gameDeveloper the developer of the game
     * @param gameGenre the genre of the game
     * @param gamePrice the price of the game
     */
    public Game(final Long gameId, final String gameName, final String gameDeveloper, final String gameGenre, final Double gamePrice) {
        this.id = gameId;
        this.name = gameName;
        this.developer = gameDeveloper;
        this.genre = gameGenre;
        this.price = gamePrice;
    }

    /**
     * Default constructor for JPA.
     */
    public Game() {
    }

    // Getters and setters with Javadoc comments

    /**
     * Clones the current game with updated information.
     *
     * @param updatedGame the updated game information
     */
    public void clone(final Game updatedGame) {
        this.setName(updatedGame.getName());
        this.setDeveloper(updatedGame.getDeveloper());
        this.setGenre(updatedGame.getGenre());
        this.setPrice(updatedGame.getPrice());
    }
}