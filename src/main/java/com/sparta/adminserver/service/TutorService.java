package com.sparta.adminserver.service;

import com.sparta.adminserver.dto.TutorRequestDto;
import com.sparta.adminserver.dto.TutorResponseDto;
import com.sparta.adminserver.entity.Tutor;
import com.sparta.adminserver.entity.enums.ManagerRoleEnum;
import com.sparta.adminserver.exception.entity.Tutor.TutorNotFoundException;
import com.sparta.adminserver.exception.entity.User.NotAllowedForNonManagerException;
import com.sparta.adminserver.jwt.JwtUtil;
import com.sparta.adminserver.repository.TutorRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorService {
    private final TutorRepository tutorRepository;
    private final JwtUtil jwtUtil;

    // 강사 등록
    public TutorResponseDto registerTutor(TutorRequestDto requestDto) {

        Tutor tutor = new Tutor(requestDto);
        tutorRepository.save(tutor);
        return new TutorResponseDto(tutor);
    }

    // 강사 정보 수정
    @Transactional
    public TutorResponseDto modifyTutor(Long tutorId, TutorRequestDto requestDto, HttpServletRequest req) {
        if (!jwtUtil.checkAuth(req, ManagerRoleEnum.MANAGER)) { // Manager 권한이 없는 경우 예외
            throw new NotAllowedForNonManagerException();
        }

        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(TutorNotFoundException::new);
        tutor.modify(requestDto);
        return new TutorResponseDto(tutor);
    }

    // 강사 조회
    public TutorResponseDto findTutor(Long tutorId) {
        // 널 처리 필요
        Tutor tutor = tutorRepository.findById(tutorId).orElseThrow(TutorNotFoundException::new);
        return new TutorResponseDto(tutor);
    }


}