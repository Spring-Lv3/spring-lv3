package com.sparta.adminserver.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureRequestDto {
    private String name;
    private Long price;
    private String comment;
    private String category;
    private Long tutorId;
}
