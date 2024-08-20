package com.example.MillionareGame.controller;

import com.example.MillionareGame.model.Game;
import com.example.MillionareGame.repository.GameRepository;
import com.example.MillionareGame.utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameUtils gameUtils;

    @PostMapping
    public Game startNewGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @GetMapping("/{id}")
    public Optional<Game> getGameById(@PathVariable("id") String id) {
        return gameRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable("id") String id, @RequestBody Game game) {
        game.setId(id);
        boolean correct = true; // Example variable; this should be passed dynamically
        gameUtils.updateGameState(game, correct);
        return gameRepository.save(game);
    }

    @PutMapping("/{id}/end")
    public Game endGame(@PathVariable("id") String id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            game.setEndedAt(new Date());
            return gameRepository.save(game);
        }
        return null;
    }

    @GetMapping("/{id}/guarantee")
    public int getGuaranteedAmount(@PathVariable("id") String id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            return gameUtils.getGuaranteedPrize(game);
        }
        return 0;
    }

    @PostMapping("/{id}/lifeline")
    public ResponseEntity<?> useLifeline(
        @PathVariable("id") String id,
        @RequestParam("lifelineType") String lifelineType) {

        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            gameUtils.applyLifeline(game, lifelineType);
            gameRepository.save(game);
            return ResponseEntity.ok(game);
        }
        return ResponseEntity.notFound().build();
    }
}
