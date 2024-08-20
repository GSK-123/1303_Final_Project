package com.example.MillionareGame.utils;

import com.example.MillionareGame.model.Game;
import com.example.MillionareGame.model.Rules;
import com.example.MillionareGame.model.Lifelines;
import com.example.MillionareGame.repository.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.MillionareGame.model.GameState;

@Component
public class GameUtils {

    @Autowired
    private RulesRepository rulesRepository;

    private Rules getRules() {
        return rulesRepository.findById("RULES_ID").orElseThrow(() -> new RuntimeException("Rules not found"));
    }

    public int calculatePrize(int questionNumber) {
        Rules rules = getRules();
        return rules.getPrizeAmount(questionNumber);
    }

    public int getGuaranteedPrize(Game game) {
        Rules rules = getRules();
        return rules.getGuaranteedAmount(game.getCurrentQuestion());
    }

    public boolean isGameOver(Game game, boolean correct) {
        return !correct && game.getCurrentQuestion() > 5;
    }

    public void updateGameState(Game game, boolean correct) {
        if (!correct) {
            game.setGameState(GameState.GAME_OVER);
            return;
        }

        int currentQuestionNumber = game.getCurrentQuestion();

        if (currentQuestionNumber == 15) {
            game.setGameState(GameState.FINAL_PRIZE_WON);
        } else if (currentQuestionNumber > 10) {
            game.setGameState(GameState.SECOND_GUARANTEE_PASSED);
        } else if (currentQuestionNumber > 5) {
            game.setGameState(GameState.FIRST_GUARANTEE_PASSED);
        }
    }

    public void applyLifeline(Game game, String lifelineType) {
        Lifelines lifelines = game.getLifelinesUsed();
        
        switch (lifelineType.toLowerCase()) {
            case "fiftyfifty":
                if (!lifelines.canUseFiftyFifty()) {
                    throw new IllegalArgumentException("Fifty-Fifty lifeline already used.");
                }
                lifelines.setFiftyFifty(true);
                break;
            case "flipthequestion":
                if (!lifelines.canUseFlipTheQuestion()) {
                    throw new IllegalArgumentException("Flip The Question lifeline already used.");
                }
                lifelines.setFlipTheQuestion(true);
                break;
            case "doubledip":
                if (!lifelines.canUseDoubleDip()) {
                    throw new IllegalArgumentException("Double Dip lifeline already used.");
                }
                lifelines.setDoubleDip(true);
                break;
            case "skipquestion":
                if (!lifelines.canUseSkipQuestion()) {
                    throw new IllegalArgumentException("Skip Question lifeline already used.");
                }
                lifelines.setSkipQuestion(true);
                break;
            default:
                throw new IllegalArgumentException("Unknown lifeline: " + lifelineType);
        }
    }
}
