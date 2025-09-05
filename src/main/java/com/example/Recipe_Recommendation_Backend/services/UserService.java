package com.example.Recipe_Recommendation_Backend.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Recipe_Recommendation_Backend.dto.UserDTO;
import com.example.Recipe_Recommendation_Backend.dto.UserLoginDTO;
import com.example.Recipe_Recommendation_Backend.exceptions.InvalidUserCredentialsException;
import com.example.Recipe_Recommendation_Backend.exceptions.UserAlreadyExistsException;
import com.example.Recipe_Recommendation_Backend.model.User;
import com.example.Recipe_Recommendation_Backend.repository.UserRepository;
import com.example.Recipe_Recommendation_Backend.security.JwtUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
     private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
   

    @Autowired
    UserService(UserRepository userRepository,AuthenticationManager authManager,JwtUtil jwtUtil,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }


    public UserDTO registerUser(User user){

        //checking if the user has already registered
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("User Already Exists");
        }        

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        
        User savedUser = userRepository.save(user);

        String token = jwtUtil.generateToken(savedUser.getUsername());
        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setUsername(savedUser.getUsername());
        userDTO.setToken(token);

        return userDTO;
    }

    public UserDTO loginUser(UserLoginDTO user){
        
        User availUser = userRepository.findByUsername(user.getUsername()).orElseThrow(()->new InvalidUserCredentialsException("Invalid User Credentials"));
        
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        
        String token = jwtUtil.generateToken(user.getUsername());
        
        UserDTO userDTO = new UserDTO();
        userDTO.setId(availUser.getId());
        userDTO.setToken(token);
        userDTO.setUsername(availUser.getUsername());

        return userDTO;
    }
}
