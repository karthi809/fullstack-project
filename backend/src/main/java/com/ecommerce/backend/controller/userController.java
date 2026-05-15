package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.userEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import com.ecommerce.backend.service.userService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "https://ecommerce-frontend-el32.vercel.app/")
public class userController {

    @Autowired
    private userService service;

    @PostMapping("/store")
    public ResponseEntity<String> storeUser(@RequestBody userEntity user){
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
}
