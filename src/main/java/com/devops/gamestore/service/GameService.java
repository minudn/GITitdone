package com.devops.gamestore.service;

import com.devops.gamestore.entity.Game;
import com.devops.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        Game existingGame = getGameById(id);
        existingGame.setName(updatedGame.getName());
        existingGame.setDeveloper(updatedGame.getDeveloper());
        existingGame.setGenre(updatedGame.getGenre());
        existingGame.setPrice(updatedGame.getPrice());
        return gameRepository.save(existingGame);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}