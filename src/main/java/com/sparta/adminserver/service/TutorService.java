package com.sparta.adminserver.service;

import com.sparta.adminserver.dto.TutorRequestDto;
import com.sparta.adminserver.dto.TutorResponseDto;
import com.sparta.adminserver.entity.Tutor;
import com.sparta.adminserver.repository.TutorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;

    // 강사 등록
    public TutorResponseDto registeredTutor(TutorRequestDto requestDto) {
        Tutor tutor = new Tutor(requestDto);
        tutorRepository.save(tutor);
        return new TutorResponseDto(tutor);
    }

    // 강사 정보 수정
    @Transactional
    public TutorResponseDto modifyTutor(Long tutorId, TutorRequestDto requestDto) {
        // 널 처리 필요
        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow();
        tutor.modify(requestDto);
        return new TutorResponseDto(tutor);
    }

    // 강사 조회
    public TutorResponseDto findTutor(Long tutorId) {
        // 널 처리 필요
        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow();
        return new TutorResponseDto(tutor);
    }
}