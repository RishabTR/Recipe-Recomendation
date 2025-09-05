package com.example.Recipe_Recommendation_Backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Recipe_Recommendation_Backend.dto.UserDTO;
import com.example.Recipe_Recommendation_Backend.dto.UserLoginDTO;
import com.example.Recipe_Recommendation_Backend.dto.UserSignUpDTO;
import com.example.Recipe_Recommendation_Backend.model.User;
import com.example.Recipe_Recommendation_Backend.services.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
 

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
       
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserSignUpDTO userSignUpDTO){
        User user = new User();
        user.setUsername(userSignUpDTO.getUsername());
        user.setPassword(userSignUpDTO.getPassword());

        UserDTO registerUser = userService.registerUser(user);

        UserDTO userDTO = new UserDTO();
        
        userDTO.setId(registerUser.getId());
        userDTO.setUsername(registerUser.getUsername());
        userDTO.setToken(registerUser.getToken());

        return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO){
        UserDTO response = userService.loginUser(userLoginDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
