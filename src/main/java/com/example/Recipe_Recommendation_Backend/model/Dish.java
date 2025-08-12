package com.example.Recipe_Recommendation_Backend.model;



import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dishName;

    
    @ManyToMany
    @JoinTable(
        name = "dish_ingredients",
        joinColumns = @JoinColumn(name="dish_id"),
        inverseJoinColumns =  @JoinColumn(name="ingredients_id") 
        )
        private List<Ingredient> ingredients;
        

    public void setName(String dishName) {
        this.dishName = dishName;
    }
    public String getName() {
        return dishName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIngridients(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }
}
