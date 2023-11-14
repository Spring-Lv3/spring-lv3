package com.sparta.adminserver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequestDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
