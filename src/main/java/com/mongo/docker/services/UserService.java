package com.mongo.docker.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mongo.docker.DTO.UserCreateDTO;
import com.mongo.docker.DTO.UserReadDTO;
import com.mongo.docker.DTO.UserUpdateDTO;
import com.mongo.docker.entities.User;
import com.mongo.docker.repositores.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public UserReadDTO save(UserCreateDTO dto){

        User user = dto.parseUser();
        User result = repository.findByEmail(dto.getEmail());

        if(result != null){
            return null;
        }

        user = repository.save(user);
        UserReadDTO userReadDTO = new UserReadDTO(
            user.getId(),
            user.getName(),
            user.getEmail());

        return userReadDTO;
    }

    public Page<UserReadDTO> findAll(Pageable pageable) {

        Page<User> result = repository.findAll(pageable);
        Page<UserReadDTO> page = result.map(
            u -> new UserReadDTO(u.getId(), u.getName(), u.getEmail()));

        return  page;
    }

    public UserReadDTO findById(String id){

        Optional<User> result = repository.findById(id);

        if(!result.isEmpty()){

            User user = result.get();
            UserReadDTO dto = new UserReadDTO(
                user.getId(), 
                user.getName(), 
                user.getEmail());

            return dto;
        }

        return null;
    }
    
    public UserReadDTO update(UserUpdateDTO dto){
        
        Optional<User> result = repository.findById(dto.getId());

        if(!result.isEmpty()){

            User userResult = result.get();
            userResult.setName(dto.getName());
            userResult.setPassword(dto.getPassword());
            BeanUtils.copyProperties(userResult, result.get(),"id");

            User user = repository.save(userResult);

            UserReadDTO userReadDTO = new UserReadDTO(
                user.getId(), 
                user.getName(), 
                user.getEmail());
            return userReadDTO;
        }

        return null;
    }
    
    public boolean delete(String id){
        
        Optional<User> result = repository.findById(id);
        
        if(!result.isEmpty()){
            repository.delete(result.get());
            return true;
        }

        return false;
    } 

}
