package com.example.MillionareGame.model;

public class Lifelines {
    private boolean fiftyFifty;
    private boolean flipTheQuestion;
    private boolean doubleDip;
    private boolean skipQuestion;

    public boolean isFiftyFifty() {
        return fiftyFifty;
    }

    public void setFiftyFifty(boolean fiftyFifty) {
        this.fiftyFifty = fiftyFifty;
    }

    public boolean isFlipTheQuestion() {
        return flipTheQuestion;
    }

    public void setFlipTheQuestion(boolean flipTheQuestion) {
        this.flipTheQuestion = flipTheQuestion;
    }

    public boolean isDoubleDip() {
        return doubleDip;
    }

    public void setDoubleDip(boolean doubleDip) {
        this.doubleDip = doubleDip;
    }

    public boolean isSkipQuestion() {
        return skipQuestion;
    }

    public void setSkipQuestion(boolean skipQuestion) {
        this.skipQuestion = skipQuestion;
    }

    public boolean canUseFiftyFifty() {
        return !fiftyFifty;
    }

    public boolean canUseFlipTheQuestion() {
        return !flipTheQuestion;
    }

    public boolean canUseDoubleDip() {
        return !doubleDip;
    }

    public boolean canUseSkipQuestion() {
        return !skipQuestion;
    }
}
