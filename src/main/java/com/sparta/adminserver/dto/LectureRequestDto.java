package com.sparta.adminserver.dto;

import com.sparta.adminserver.annotation.MyEnum.MyEnum;
import com.sparta.adminserver.entity.enums.LectureCategoryEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureRequestDto {
    private String name;
    private Long price;
    private String comment;
    @MyEnum(enumClass = LectureCategoryEnum.class)
    private String category;
    private Long tutorId;
}