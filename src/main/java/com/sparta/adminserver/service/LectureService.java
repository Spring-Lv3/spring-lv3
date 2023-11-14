package com.sparta.adminserver.service;

import com.sparta.adminserver.dto.LectureRequestDto;
import com.sparta.adminserver.dto.LectureResponseDto;
import com.sparta.adminserver.entity.Lecture;
import com.sparta.adminserver.entity.Tutor;
import com.sparta.adminserver.entity.enums.ManagerRoleEnum;
import com.sparta.adminserver.exception.entity.Tutor.TutorNotFoundException;
import com.sparta.adminserver.exception.entity.User.NotAllowedForNonManagerException;
import com.sparta.adminserver.exception.entity.lecture.LectureNotFoundException;
import com.sparta.adminserver.jwt.JwtUtil;
import com.sparta.adminserver.repository.LectureRepository;
import com.sparta.adminserver.repository.TutorRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;
    private final JwtUtil jwtUtil;

    // 강의 등록
    @Transactional
    public LectureResponseDto registerLecture(LectureRequestDto requestDto) {
        // 널 처리 필요
        Tutor tutor = tutorRepository.findById(requestDto.getTutorId()).orElseThrow(TutorNotFoundException::new);
        Lecture lecture = new Lecture(requestDto);
        // Lecture Entity와 Tutor Entity 연결
        tutor.addLecture(lecture);
        lectureRepository.save(lecture);
        return new LectureResponseDto(lecture);
    }

    // 강의 정보 수정
    @Transactional
    public LectureResponseDto modifyLecture(Long lectureId, LectureRequestDto requestDto, HttpServletRequest httpreq) {
        if(!jwtUtil.checkAuth(httpreq, ManagerRoleEnum.MANAGER)){
            throw new NotAllowedForNonManagerException();
        }
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(LectureNotFoundException::new);
        lecture.modify(requestDto);
        return new LectureResponseDto(lecture);
    }

    // 강의 조회
    public LectureResponseDto findLecture(Long lectureId) {
        // 널 처리 필요
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(LectureNotFoundException::new);
        return new LectureResponseDto(lecture);
    }

    // 선택한 강사가 촬영한 강의 목록 조회
    public List<LectureResponseDto> findLectureByTutor(Long tutorId) {
        tutorRepository.findById(tutorId).orElseThrow(TutorNotFoundException::new);
        return lectureRepository.findByTutor_TutorIdOrderByRegisteredAtDesc(tutorId).stream().map(LectureResponseDto::new).toList();
    }

    // 카테고리별 강의 목록 조회
    public List<LectureResponseDto> findLectureByCategory(String category) {
        return lectureRepository.findByCategoryOrderByRegisteredAtDesc(category).stream().map(LectureResponseDto::new).toList();
    }

    // 강의 삭제
    public ResponseEntity<?> deleteLecture(Long lectureId) {
        // TODO
        return ResponseEntity.ok().build();
    }
}