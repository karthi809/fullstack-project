package com.ecommerce.backend.service;

import com.ecommerce.backend.model.userEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.backend.repository.userRepo;

import java.util.Optional;

@Service
public class userService {
    @Autowired
    private userRepo repo;

    public String addUser(userEntity user){
        Optional<userEntity> existing=repo.findByEmail(user.getEmail());
        if(existing.isPresent()){
            return "EMAIL_EXISTS";
        }
        repo.save(user);
        return "SUCCESS";
    }
}
