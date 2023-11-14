package com.sparta.adminserver.dto;

import com.sparta.adminserver.entity.Lecture;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureResponseDto {
    private String name;
    private Long price;
    private String comment;
    private String category;
    private String tutor_name;
    private LocalDateTime registeredAt;

    public LectureResponseDto(Lecture lecture) {
        this.name = lecture.getName();
        this.price = lecture.getPrice();
        this.comment = lecture.getComment();
        this.category = lecture.getCategory();
        this.tutor_name = lecture.getTutor().getName();
        this.registeredAt = lecture.getRegisteredAt();
    }
}
