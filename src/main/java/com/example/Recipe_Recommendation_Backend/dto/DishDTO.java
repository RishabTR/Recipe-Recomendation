package com.example.Recipe_Recommendation_Backend.dto;

import java.util.List;

public class DishDTO {
    String dishName;
    List<String> ingredientsList;
    
    
    public String getDishName(){
        return dishName;
    }
    public void setDishName(String dishName){
        this.dishName = dishName;
    }

    public List<String> getIngredientsList(){
        return ingredientsList;
    }
    public void setIngredientsList(List<String> ingredientsList){
        this.ingredientsList = ingredientsList;
    }
}
