package com.sparta.adminserver.entity;

import com.sparta.adminserver.dto.LectureRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecture_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long career;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(length = 500)
    private String comment;

    public Lecture(LectureRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.comment = requestDto.getComment();
    }
}