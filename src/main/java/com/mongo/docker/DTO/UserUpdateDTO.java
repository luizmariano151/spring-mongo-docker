package com.mongo.docker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserUpdateDTO {

    private String id;
    private String name;
    private String password;
    
}
