package com.mongo.docker.entities;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.ToString;

@Data
@Document
@ToString
public class User {

    private String id;
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
    }
    

}
