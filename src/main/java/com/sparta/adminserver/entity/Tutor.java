package com.sparta.adminserver.entity;

import com.sparta.adminserver.dto.TutorRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tutor_id;

    @OneToMany(mappedBy = "tutor")
    private List<Lecture> lectures = new ArrayList<>();

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

    public void addLecture(Lecture lecture) {
        this.lectures.add(lecture);
        lecture.setTutor(this);
    }

    public Tutor(TutorRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.company = requestDto.getCompany();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.comment = requestDto.getComment();
    }
}
