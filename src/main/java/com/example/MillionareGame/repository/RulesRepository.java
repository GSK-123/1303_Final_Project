package com.example.MillionareGame.repository;

import com.example.MillionareGame.model.Rules;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulesRepository extends MongoRepository<Rules, String> {

}
