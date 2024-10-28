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

    private String name;
    private String developer;
    private String genre;
    private Double price;

    /**
     * Constructs a new Game with the specified details.
     *
     * @param id the ID of the game
     * @param name the name of the game
     * @param developer the developer of the game
     * @param genre the genre of the game
     * @param price the price of the game
     */
    public Game(final Long id, final String name, final String developer, final String genre, final Double price) {
        this.id = id;
        this.name = name;
        this.developer = developer;
        this.genre = genre;
        this.price = price;
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