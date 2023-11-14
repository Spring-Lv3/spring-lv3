package com.sparta.adminserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorRequestDto {
    private String name;
    private Long career;
    private String company;
    private String phoneNumber;
    private String comment;
}