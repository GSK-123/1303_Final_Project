package com.example.MillionareGame.repository;

import com.example.MillionareGame.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
