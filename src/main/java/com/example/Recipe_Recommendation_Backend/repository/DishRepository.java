package com.example.Recipe_Recommendation_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Recipe_Recommendation_Backend.model.Dish;

public interface DishRepository extends JpaRepository<Dish,Long>{
    
}
