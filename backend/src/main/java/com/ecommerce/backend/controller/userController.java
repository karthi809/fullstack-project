package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.userEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import com.ecommerce.backend.service.userService;

import java.security.Key;
import java.util.Map;

@RestController
@RequestMapping("/api/users")

@CrossOrigin(origins = "https://ecommerce-frontend-el32.vercel.app")
public class userController {

    @Autowired
    private userService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody userEntity user){
        String result=service.addUser(user);

        return switch (result){
            case "EMAIL_EXITS" ->ResponseEntity.status(409).body(
                    "Email already registered,please login."
            );
            case "SUCCESS"->ResponseEntity.ok(
                    "Registered successfully."
            );
            default -> ResponseEntity.status(500).body(
                    "Something went wrong."
            );
        };
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String,String> credentials){

        String email= credentials.get("email");
        String password= credentials.get("password");
        String result=service.loginUser(email,password);

        return switch (result){
            case "USER_NOT_FOUND" ->ResponseEntity.status(409).body(
                    "No account found. Please register first."
            );
            case "WRONG_PASSWORD" ->ResponseEntity.status(401).body(
                    "Incorrect password."
            );
            case "SUCCESS"->ResponseEntity.ok(
                    "Login successfully."
            );
            default -> ResponseEntity.status(500).body(
                    "Something went wrong."
            );
        };
    }
}
