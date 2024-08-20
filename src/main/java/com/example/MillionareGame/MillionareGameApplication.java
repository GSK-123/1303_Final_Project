package com.example.MillionareGame;

import com.example.MillionareGame.model.Rules;
import com.example.MillionareGame.repository.RulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MillionareGameApplication implements CommandLineRunner {

    @Autowired
    private RulesRepository rulesRepository;

    public static void main(String[] args) {
        SpringApplication.run(MillionareGameApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (rulesRepository.findById("RULES_ID").isEmpty()) {
            Rules rules = new Rules();
            rules.setId("RULES_ID");
            rulesRepository.save(rules);
            System.out.println("Initialized game rules.");
        }
    }
}
