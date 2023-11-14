package com.sparta.adminserver.repository;

import com.sparta.adminserver.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
