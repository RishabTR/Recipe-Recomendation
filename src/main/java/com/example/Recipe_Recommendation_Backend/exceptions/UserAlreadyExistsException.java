package com.example.Recipe_Recommendation_Backend.exceptions;

public class UserAlreadyExistsException extends  RuntimeException{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
