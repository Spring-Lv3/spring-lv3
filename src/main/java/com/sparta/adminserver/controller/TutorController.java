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

    @PostMapping("")
    public TutorResponseDto registerTutor(@RequestBody TutorRequestDto requestDto) {
        return tutorService.registerTutor(requestDto);
    }

    @PutMapping("/{tutor_id}")
    public TutorResponseDto modifyTutor(@PathVariable Long tutor_id, @RequestBody TutorRequestDto requestDto) {
        return tutorService.modifyTutor(tutor_id, requestDto);
    }

    @GetMapping("/{tutor_id}")
    public TutorResponseDto findTutor(@PathVariable Long tutor_id) {
        return tutorService.findTutor(tutor_id);
    }
}
