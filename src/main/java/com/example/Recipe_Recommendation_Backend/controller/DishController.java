package com.example.Recipe_Recommendation_Backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Recipe_Recommendation_Backend.dto.DishDTO;
import com.example.Recipe_Recommendation_Backend.model.Dish;
import com.example.Recipe_Recommendation_Backend.model.Ingredient;
import com.example.Recipe_Recommendation_Backend.services.DishService;

@RestController
@RequestMapping("/dishes")
public class DishController {
    private DishService dishService;

    public DishController(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping("/all")
    public List<Dish> getAllDishes(){
        return dishService.getAllDishes();
    }
    @PostMapping("/bestDishes")
    public List<DishDTO> getBestDishes(@RequestBody List<String> ingredients){
        List<Dish> bestDishes = dishService.getMatchingDishes(ingredients);
        List<DishDTO> result =  new ArrayList<>();
        
        
        for(Dish dish : bestDishes){

            DishDTO dishDTO = new DishDTO();
            dishDTO.setDishName(dish.getName());
        
            List<String> ingredientsList = dish.getIngredients().stream()
            .map(Ingredient::getName)
            .toList();

            dishDTO.setIngredientsList(ingredientsList);

            result.add(dishDTO);
        }

        return result;
    }
}
