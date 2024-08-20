package com.example.MillionareGame.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.MillionareGame.repository")
public class MongoConfig {
    
}
