package com.sparta.adminserver.service;

import com.sparta.adminserver.dto.LectureRequestDto;
import com.sparta.adminserver.dto.LectureResponseDto;
import com.sparta.adminserver.entity.Lecture;
import com.sparta.adminserver.repository.LectureRepository;
import com.sparta.adminserver.repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    public LectureResponseDto registeredLecture(LectureRequestDto requestDto) {
        Lecture lecture = new Lecture(requestDto);
        // 널 처리 필요
        lecture.setTutor(tutorRepository.findById(requestDto.getTutorId()).orElseThrow());
        return new LectureResponseDto(lecture);
    }
}

