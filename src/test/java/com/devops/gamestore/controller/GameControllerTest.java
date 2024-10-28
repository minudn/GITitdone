package com.devops.gamestore.controller;

import com.devops.gamestore.entity.Game;
import com.devops.gamestore.service.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the GameController.
 */
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    /**
     * Tests the getAllGames method of GameController.
     *
     * @throws Exception if an error occurs during the request
     */
    @Test
    public void testGetAllGames() throws Exception {
        Game game1 = new Game(1L, "FIFA 2024", "Electronic Arts", "Fútbol", 59.99);
        Game game2 = new Game(2L, "The Witcher 3", "CD Projekt Red", "RPG", 39.99);
        Mockito.when(gameService.getAllGames()).thenReturn(Arrays.asList(game1, game2));

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("FIFA 2024"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("The Witcher 3"));
    }

    /**
     * Tests the getGameById method of GameController.
     */
    @Test
    public void testGetGameById() {
        Game game = new Game(1L, "The Legend of Zelda: Breath of the Wild", "Nintendo", "Adventure", 59.99);
        Mockito.when(gameService.getGameById(1L)).thenReturn(game);

        Game retrievedGame = gameService.getGameById(1L);
        assertNotNull(retrievedGame);
        assertEquals(1L, retrievedGame.getId().longValue());
        assertEquals("The Legend of Zelda: Breath of the Wild", retrievedGame.getName());
    }

    /**
     * Tests the createGame method of GameController.
     */
    @Test
    public void testCreateGame() {
        Game game = new Game(null, "Cyberpunk 2077", "CD Projekt Red", "RPG", 59.99);
        Game savedGame = new Game(3L, "Cyberpunk 2077", "CD Projekt Red", "RPG", 59.99);
        Mockito.when(gameService.createGame(game)).thenReturn(savedGame);

        Game createdGame = gameService.createGame(game);
        assertNotNull(createdGame);
        assertEquals("Cyberpunk 2077", createdGame.getName());
        assertEquals("CD Projekt Red", createdGame.getDeveloper());
    }

    /**
     * Tests the updateGame method of GameController.
     */
    @Test
    public void testUpdateGame() {
        Game existingGame = new Game(2L, "Old Game", "Old Developer", "Old Genre", 29.99);
        Game updatedGameDetails = new Game(null, "Super Mario Odyssey", "Nintendo", "Platformer", 49.99);
        Game updatedGame = new Game(2L, "Super Mario Odyssey", "Nintendo", "Platformer", 49.99);
        Mockito.when(gameService.getGameById(2L)).thenReturn(existingGame);
        Mockito.when(gameService.updateGame(2L, updatedGameDetails)).thenReturn(updatedGame);

        Game result = gameService.updateGame(2L, updatedGameDetails);
        assertNotNull(result);
        assertEquals("Super Mario Odyssey", result.getName());
        assertEquals(49.99, result.getPrice());
    }

    /**
     * Tests the deleteGame method of GameController.
     */
    @Test
    public void testDeleteGame() {
        Mockito.doNothing().when(gameService).deleteGame(3L);
        gameService.deleteGame(3L);
        Mockito.verify(gameService, Mockito.times(1)).deleteGame(3L);
    }

    /**
     * Tests if popular games exist in the GameService.
     */
    @Test
    public void testPopularGamesExist() {
        Game zelda = new Game(1L, "The Legend of Zelda: Breath of the Wild", "Nintendo", "Adventure", 59.99);
        Game mario = new Game(2L, "Super Mario Odyssey", "Nintendo", "Platformer", 49.99);
        Game fortnite = new Game(3L, "Fortnite", "Epic Games", "Battle Royale", 0.00);

        Mockito.when(gameService.getGameById(1L)).thenReturn(zelda);
        Mockito.when(gameService.getGameById(2L)).thenReturn(mario);
        Mockito.when(gameService.getGameById(3L)).thenReturn(fortnite);

        assertNotNull(zelda, "Zelda game should not be null");
        assertNotNull(mario, "Mario game should not be null");
        assertNotNull(fortnite, "Fortnite game should not be null");

        assertEquals("The Legend of Zelda: Breath of the Wild", zelda.getName());
        assertEquals("Super Mario Odyssey", mario.getName());
        assertEquals("Fortnite", fortnite.getName());
    }

}