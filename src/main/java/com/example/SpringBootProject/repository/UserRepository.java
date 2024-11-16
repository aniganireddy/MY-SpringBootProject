package com.example.SpringBootProject.repository;

import com.example.SpringBootProject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
