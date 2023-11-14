package com.sparta.adminserver.config;

import com.sparta.adminserver.jwt.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public JwtUtil JwtUtil(){
        return new JwtUtil();
    }
}
