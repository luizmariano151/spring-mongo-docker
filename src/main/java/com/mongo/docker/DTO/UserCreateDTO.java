package com.mongo.docker.DTO;

import com.mongo.docker.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateDTO {

    private String id;
    private String name;
    private String email;
    private String password;

    public User parseUser(){
        User user = new User( 
            this.name, 
            this.email, 
            this.password);
        return user;
    }
    
}
