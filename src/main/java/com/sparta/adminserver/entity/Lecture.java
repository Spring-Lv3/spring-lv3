package com.sparta.adminserver.entity;

import com.sparta.adminserver.dto.LectureRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecture_id;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(length = 500)
    private String comment;

    @Column(nullable = false)
    private String category;

//    @CreatedDate
//    @Column(updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime registeredAt;

    public Lecture(LectureRequestDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.comment = requestDto.getComment();
        this.category = requestDto.getCategory();
    }
}
