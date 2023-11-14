package com.sparta.adminserver.exception.jwt;

public class JwtTokenNotFoundException extends RuntimeException{
    public JwtTokenNotFoundException() {
        super("jwt 토큰이 없습니다.");
    }
}
