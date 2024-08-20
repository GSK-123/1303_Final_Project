package com.example.MillionareGame.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "games")
public class Game {
	@Id
    private String id;
    private String playerName;
    private int currentQuestion;
    private Lifelines lifelinesUsed = new Lifelines();
    private int moneyWon;
    private Date startedAt;
    private Date endedAt;
    private GameState gameState = GameState.START;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Lifelines getLifelinesUsed() {
        return lifelinesUsed;
    }

    public void setLifelinesUsed(Lifelines lifelinesUsed) {
        this.lifelinesUsed = lifelinesUsed;
    }

    public int getMoneyWon() {
        return moneyWon;
    }

    public void setMoneyWon(int moneyWon) {
        this.moneyWon = moneyWon;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public boolean canUseFiftyFifty() {
        return !lifelinesUsed.isFiftyFifty();
    }

    public boolean canUseFlipTheQuestion() {
        return !lifelinesUsed.isFlipTheQuestion();
    }

    public boolean canUseDoubleDip() {
        return !lifelinesUsed.isDoubleDip();
    }

    public boolean canUseSkipQuestion() {
        return !lifelinesUsed.isSkipQuestion();
    }
}



