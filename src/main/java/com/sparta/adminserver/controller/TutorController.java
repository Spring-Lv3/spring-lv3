package com.sparta.adminserver.controller;

import com.sparta.adminserver.dto.TutorRequestDto;
import com.sparta.adminserver.dto.TutorResponseDto;
import com.sparta.adminserver.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tutor")
public class TutorController {
    private final TutorService tutorService;

    @PostMapping("/")
    public TutorResponseDto registeredTutor(@RequestBody TutorRequestDto requestDto) {
        return tutorService.registeredTutor(requestDto);
    }
}
