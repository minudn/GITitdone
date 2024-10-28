package com.devops.gamestore.entity;

import jakarta.persistence.*;

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
    public Game(Long id, String name, String developer, String genre, Double price) {
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

    /**
     * Returns the ID of the game.
     *
     * @return the ID of the game
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the game.
     *
     * @param id the new ID of the game
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the game.
     *
     * @return the name of the game
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the game.
     *
     * @param name the new name of the game
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the developer of the game.
     *
     * @return the developer of the game
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * Sets the developer of the game.
     *
     * @param developer the new developer of the game
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    /**
     * Returns the genre of the game.
     *
     * @return the genre of the game
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the game.
     *
     * @param genre the new genre of the game
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Returns the price of the game.
     *
     * @return the price of the game
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the game.
     *
     * @param price the new price of the game
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Clones the current game with updated information.
     *
     * @param updatedGame the updated game information
     */
    public void clone(Game updatedGame) {
        this.setName(updatedGame.getName());
        this.setDeveloper(updatedGame.getDeveloper());
        this.setGenre(updatedGame.getGenre());
        this.setPrice(updatedGame.getPrice());
    }
}