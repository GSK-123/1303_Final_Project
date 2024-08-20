package com.example.MillionareGame.controller;

import com.example.MillionareGame.model.Question;
import com.example.MillionareGame.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/random")
    public List<Question> getRandomQuestions() {
        List<Question> allQuestions = questionRepository.findAll();

        List<Question> easyQuestions = allQuestions.stream()
                .filter(q -> q.getDifficulty() == 1)
                .collect(Collectors.toList());
        List<Question> mediumQuestions = allQuestions.stream()
                .filter(q -> q.getDifficulty() == 2)
                .collect(Collectors.toList());
        List<Question> hardQuestions = allQuestions.stream()
                .filter(q -> q.getDifficulty() == 3)
                .collect(Collectors.toList());
        List<Question> veryHardQuestions = allQuestions.stream()
                .filter(q -> q.getDifficulty() == 4)
                .collect(Collectors.toList());

        if (easyQuestions.size() < 5 || mediumQuestions.size() < 5 || hardQuestions.size() < 4 || veryHardQuestions.size() < 1) {
            throw new RuntimeException("Not enough questions available in one of the categories.");
        }

        Collections.shuffle(easyQuestions);
        Collections.shuffle(mediumQuestions);
        Collections.shuffle(hardQuestions);
        Collections.shuffle(veryHardQuestions);

        return List.of(
                easyQuestions.subList(0, 5),
                mediumQuestions.subList(0, 5),
                hardQuestions.subList(0, 4),
                veryHardQuestions.subList(0, 1)
        ).stream().flatMap(List::stream).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable("id") String id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

}
