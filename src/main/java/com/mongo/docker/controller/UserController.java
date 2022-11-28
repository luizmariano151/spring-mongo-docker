package com.mongo.docker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.docker.DTO.UserCreateDTO;
import com.mongo.docker.DTO.UserReadDTO;
import com.mongo.docker.DTO.UserUpdateDTO;
import com.mongo.docker.services.UserService;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService service;
    
    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<UserReadDTO> save(@RequestBody UserCreateDTO dto){
        
        UserReadDTO result = service.save(dto);

        if(result == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<Page<UserReadDTO>> findAll(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDTO> findById(@PathVariable("id") String id){

        UserReadDTO result = service.findById(id);

        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<UserReadDTO> update(@RequestBody UserUpdateDTO dto){
        
        UserReadDTO result = service.update(dto);

        if(result == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") String id){
        
        boolean result = service.delete(id);

        if(!result){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
