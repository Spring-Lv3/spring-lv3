package com.sparta.adminserver.dto;

import com.sparta.adminserver.entity.Tutor;
import lombok.Getter;

@Getter
public class TutorResponseDto {
    private String name;
    private Long career;
    private String company;
    private String phoneNumber;
    private String comment;

    public TutorResponseDto(Tutor tutor) {
        this.name = tutor.getName();
        this.career = tutor.getCareer();
        this.company = tutor.getCompany();
        this.phoneNumber = tutor.getPhoneNumber();
        this.comment = tutor.getComment();
    }
}
