package com.sparta.adminserver.controller;

import com.sparta.adminserver.config.swagger.ApiDocument;
import com.sparta.adminserver.dto.SignInRequestDto;
import com.sparta.adminserver.dto.SignUpRequestDto;
import com.sparta.adminserver.entity.enums.ManagerRoleEnum;
import com.sparta.adminserver.jwt.JwtUtil;
import com.sparta.adminserver.service.SignService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "sign Controller", description = "회원가입, 로그인 관련 컨트롤러")
@Controller
@RequiredArgsConstructor
public class SignController {

    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final SignService signService;

    @ApiDocument
    @Operation(summary = "signup", description = "회원 가입")
    //form 형식 데이터를 받기 위해 @ModelAttribute 사용
    // Valid 에서 걸릴 시 MethodArgumentNotValidException발생
    @PostMapping("/signup")
    public void signup(@ModelAttribute @Valid SignUpRequestDto req) {
        signService.signup(req);
    }

    @ApiDocument
    @Operation(summary = "signin", description = "로그인")
    @PostMapping("/signin")
    public void signin(@ModelAttribute @Valid SignInRequestDto req, HttpServletResponse res) {
        try {
            signService.signin(req, res);
        } catch (Exception e) {
            throw new RuntimeException("로그인 실패");
        }
    }
}
