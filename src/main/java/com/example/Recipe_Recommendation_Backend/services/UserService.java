package com.example.Recipe_Recommendation_Backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Recipe_Recommendation_Backend.model.User;
import com.example.Recipe_Recommendation_Backend.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User registerUser(User user){

        //checking if the user has already registered
        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new RuntimeException("User Already Exists");
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        
        return userRepository.save(user);
    }
}
