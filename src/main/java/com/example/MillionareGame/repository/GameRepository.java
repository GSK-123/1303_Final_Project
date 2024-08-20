package com.example.MillionareGame.repository;

import com.example.MillionareGame.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {
	
}
