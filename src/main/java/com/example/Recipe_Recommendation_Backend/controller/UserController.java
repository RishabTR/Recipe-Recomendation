package com.example.Recipe_Recommendation_Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Recipe_Recommendation_Backend.dto.UserDTO;
import com.example.Recipe_Recommendation_Backend.dto.UserSignUpDTO;
import com.example.Recipe_Recommendation_Backend.model.User;
import com.example.Recipe_Recommendation_Backend.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserSignUpDTO userSignUpDTO){
        User user = new User();
        user.setUsername(userSignUpDTO.getUsername());
        user.setPassword(userSignUpDTO.getPassword());

        User registerUser = userService.registerUser(user);

        UserDTO userDTO = new UserDTO();
        
        userDTO.setId(registerUser.getId());
        userDTO.setUsername(registerUser.getUsername());

        return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
    }
}
