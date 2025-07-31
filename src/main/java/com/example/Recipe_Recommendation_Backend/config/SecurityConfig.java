package com.example.Recipe_Recommendation_Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for API usage (enable for production with token)
            .authorizeHttpRequests()
                .requestMatchers("/api/auth/signup", "/api/auth/login").permitAll() // Allow signup and login
                .anyRequest().authenticated() // Protect other endpoints
            .and()
            .httpBasic(); // Or formLogin(), or your custom authentication if needed

        return http.build();
    }
}
