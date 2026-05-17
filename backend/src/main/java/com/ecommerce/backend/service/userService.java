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

    public  String loginUser(String email,String password){
        Optional<userEntity> userOpt=repo.findByEmail(email);

        if(userOpt.isEmpty()){
            return "USER_NOT_FOUND";
        }
        userEntity user=userOpt.get();
        if(!user.getPassword().equals(password)){
            return "WRONG_PASSWORD";
        }
        return "SUCCESS";
    }
}
