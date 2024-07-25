package com.example.test.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.test.entity.User;
import com.example.test.rest.IUser;
import com.example.test.services.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController implements IUser {

    private final UserService userService;
    @Override
    public ResponseEntity<User> addUser(User user) {
        return ResponseEntity.ok(userService.addUser( user ));
    }
    
}
