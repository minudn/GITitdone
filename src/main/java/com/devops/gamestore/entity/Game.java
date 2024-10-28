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
    /**
     * The ID of the game.
     */
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
    public Game(final Long gameId, final String gameName, final String gameDeveloper,
                final String gameGenre, final Double gamePrice) {
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

    /**
     * Gets the ID of the game.
     *
     * @return the ID of the game
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the game.
     *
     * @param id the ID of the game
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the game.
     *
     * @return the name of the game
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the game.
     *
     * @param name the name of the game
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the developer of the game.
     *
     * @return the developer of the game
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * Sets the developer of the game.
     *
     * @param developer the developer of the game
     */
    public void setDeveloper(final String developer) {
        this.developer = developer;
    }

    /**
     * Gets the genre of the game.
     *
     * @return the genre of the game
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the game.
     *
     * @param genre the genre of the game
     */
    public void setGenre(final String genre) {
        this.genre = genre;
    }

    /**
     * Gets the price of the game.
     *
     * @return the price of the game
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the game.
     *
     * @param price the price of the game
     */
    public void setPrice(final Double price) {
        this.price = price;
    }
}

