package com.example.Recipe_Recommendation_Backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component    //
public class JwtUtil {
    private final String secret = "qwertyuiopasdfghjklzxcvbnmasdfghjklwertyuiosxdcfvgbhnjmk";
    private final long expiration = 60*60*1000;
    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(String username){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+expiration)).signWith(key,SignatureAlgorithm.HS256).compact();
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenExpired(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
    public boolean validateToken(String token,String usernamme){
        return (usernamme.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
