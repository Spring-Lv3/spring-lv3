package com.sparta.adminserver.controller;

import com.sparta.adminserver.config.swagger.ApiDocument;
import com.sparta.adminserver.dto.SignInRequestDto;
import com.sparta.adminserver.dto.SignUpRequestDto;
import com.sparta.adminserver.entity.User;
import com.sparta.adminserver.jwt.JwtRequired;
import com.sparta.adminserver.jwt.JwtUtil;
import com.sparta.adminserver.service.SignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final SignService signService;

    @JwtRequired
    @ApiDocument
    @Operation(summary = "test", description = "test")
    @GetMapping("/test")
    public String getNull(HttpServletRequest req){
        User user = (User) req.getAttribute("user");
        System.out.println("user.getEmail() = " + user.getEmail());
        return "12";
    }
}
