package com.example.Recipe_Recommendation_Backend.dto;

public class UserLoginDTO {
    private String username;
    private String password;


    //getters and setters

    public void setUsername(String username){
        this.username=username;
    }
    public String getUsername(){
        return username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}