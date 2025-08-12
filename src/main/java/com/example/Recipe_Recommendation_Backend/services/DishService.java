package com.example.Recipe_Recommendation_Backend.services;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Recipe_Recommendation_Backend.model.Dish;
import com.example.Recipe_Recommendation_Backend.model.Ingredient;
import com.example.Recipe_Recommendation_Backend.repository.DishRepository;

@Service
public class DishService {
    private DishRepository dishRepository;


    
    public DishService(DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }

    public List<Dish> getAllDishes(){
        return dishRepository.findAll();
    }

    public List<Dish> getMatchingDishes(List<String> ingredientsList){


        Set<String> inputSet = ingredientsList.stream()
        .map(String::toLowerCase)
        .collect(Collectors.toSet());

        long bestScore = 0;
        List<Dish> allDishes = dishRepository.findAll();
        List<Dish> bestChoiceOfDishes = new ArrayList<>();

        for(Dish dish : allDishes){
            long matchCount = dish.getIngredients().stream()
            .map(Ingredient::getName)
            .map(String::toLowerCase)
            .filter(inputSet::contains)
            .count();


            if(matchCount > bestScore){
                bestScore = matchCount;
                bestChoiceOfDishes.clear();
                bestChoiceOfDishes.add(dish);
            }
            else{
                if(matchCount == bestScore) bestChoiceOfDishes.add(dish);
            }
        }

        return bestChoiceOfDishes;
    }
}
