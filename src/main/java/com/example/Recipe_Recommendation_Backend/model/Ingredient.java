package com.example.Recipe_Recommendation_Backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="Ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    String ingredientName;


    public void setName(String ingredientName){
        this.ingredientName=ingredientName;
    }
    public String getName(){
        return ingredientName;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

}

