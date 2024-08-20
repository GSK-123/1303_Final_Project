package com.example.MillionareGame.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rules")
public class Rules {

    @Id
    private String id;
    private int[] guaranteedAmounts = {1000, 32000}; // Guarantees at question 5 and 10
    private int finalPrize = 1000000; // Final prize at question 15
    private int[] prizeValues = {100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 250000, 500000, 1000000};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[] getGuaranteedAmounts() {
        return guaranteedAmounts;
    }

    public void setGuaranteedAmounts(int[] guaranteedAmounts) {
        this.guaranteedAmounts = guaranteedAmounts;
    }

    public int getFinalPrize() {
        return finalPrize;
    }

    public void setFinalPrize(int finalPrize) {
        this.finalPrize = finalPrize;
    }

    public int[] getPrizeValues() {
        return prizeValues;
    }

    public void setPrizeValues(int[] prizeValues) {
        this.prizeValues = prizeValues;
    }

    public int getPrizeAmount(int questionNumber) {
        if (questionNumber >= 1 && questionNumber <= prizeValues.length) {
            return prizeValues[questionNumber - 1];
        } else {
            throw new IllegalArgumentException("Invalid question number");
        }
    }

    public int getGuaranteedAmount(int questionNumber) {
        for (int i = guaranteedAmounts.length - 1; i >= 0; i--) {
            int guaranteedLevel = prizeValues.length / guaranteedAmounts.length * (i + 1);
            if (questionNumber >= guaranteedLevel) {
                return guaranteedAmounts[i];
            }
        }
        return 0;
    }
}
