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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

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
}