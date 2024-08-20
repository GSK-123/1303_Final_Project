package com.example.MillionareGame.controller;

import com.example.MillionareGame.model.Game;
import com.example.MillionareGame.repository.GameRepository;
import com.example.MillionareGame.utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/rules")
public class RuleController {

    @Autowired
    private GameUtils gameUtils;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/prize/{questionNumber}")
    public int getPrizeAmount(@PathVariable int questionNumber) {
        return gameUtils.calculatePrize(questionNumber);
    }

    @GetMapping("/guarantee/{gameId}")
    public int getGuaranteedAmount(@PathVariable String gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        return gameUtils.getGuaranteedPrize(game);
    }

    @PutMapping("/updateState/{gameId}")
    public void updateGameState(@PathVariable String gameId, @RequestParam boolean correct) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        gameUtils.updateGameState(game, correct);
        gameRepository.save(game);
    }
}
