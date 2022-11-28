package com.mongo.docker.repositores;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongo.docker.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    
    User findByEmail(String email);
}
