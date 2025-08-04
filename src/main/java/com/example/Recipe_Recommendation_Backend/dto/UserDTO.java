package com.example.Recipe_Recommendation_Backend.dto;



//de-serializing the java object into json
public class UserDTO {
    private Long id;
    private String username;

    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }    
}
