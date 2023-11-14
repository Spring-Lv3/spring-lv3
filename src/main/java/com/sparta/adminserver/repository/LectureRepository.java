package com.sparta.adminserver.repository;

import com.sparta.adminserver.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByCategoryOrderByRegisteredAtDesc(String category);
    List<Lecture> findByTutor_TutorIdOrderByRegisteredAtDesc(Long tutorId);
}
