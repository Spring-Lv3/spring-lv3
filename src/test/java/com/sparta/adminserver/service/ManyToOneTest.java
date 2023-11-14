package com.sparta.adminserver.service;

import com.sparta.adminserver.entity.Lecture;
import com.sparta.adminserver.entity.Tutor;
import com.sparta.adminserver.repository.LectureRepository;
import com.sparta.adminserver.repository.TutorRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@Transactional
@SpringBootTest
public class  ManyToOneTest {

    @Autowired
    LectureRepository lectureRepository;
    @Autowired
    TutorRepository tutorRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("DB 넣기")
    void test1() {
        Tutor tutor = tutorRepository.findById(1L).orElseThrow();

        Lecture lecture = new Lecture();
        lecture.setName("DEF");
        lecture.setTutor(tutor);
        lecture.setPrice(13000L);
        lecture.setCategory("Spring");
        lecture.setComment("Hello, World!");
        lectureRepository.save(lecture);


        tutor.addLecture(lecture);
        for (Lecture lec : tutor.getLectures()) {
            System.out.println("lec.getName() = " + lec.getName());
        }
    }
}