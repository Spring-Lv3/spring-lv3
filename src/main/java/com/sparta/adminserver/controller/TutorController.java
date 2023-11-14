package com.sparta.adminserver.controller;

import com.sparta.adminserver.config.swagger.ApiDocument;
import com.sparta.adminserver.dto.TutorRequestDto;
import com.sparta.adminserver.dto.TutorResponseDto;
import com.sparta.adminserver.jwt.JwtRequired;
import com.sparta.adminserver.service.TutorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tutor")
public class TutorController {
    private final TutorService tutorService;

    @JwtRequired
    @ApiDocument
    @Operation(summary = "test", description = "test")
    @PostMapping("")
    public TutorResponseDto registerTutor(@RequestBody TutorRequestDto requestDto, HttpServletRequest req) {
        return tutorService.registerTutor(requestDto, req);
    }

    @JwtRequired
    @ApiDocument
    @Operation(summary = "test", description = "test")
    @PutMapping("/{tutor_id}")
    public TutorResponseDto modifyTutor(@PathVariable Long tutor_id, @RequestBody TutorRequestDto requestDto) {
        return tutorService.modifyTutor(tutor_id, requestDto);
    }

    @JwtRequired
    @ApiDocument
    @Operation(summary = "test", description = "test")
    @GetMapping("/{tutor_id}")
    public TutorResponseDto findTutor(@PathVariable Long tutor_id) {
        return tutorService.findTutor(tutor_id);
    }
}
