package com.example.Recipe_Recommendation_Backend.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Recipe_Recommendation_Backend.model.User;



public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
}
