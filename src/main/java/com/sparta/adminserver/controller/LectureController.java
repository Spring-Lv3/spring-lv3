package com.sparta.adminserver.controller;

import com.sparta.adminserver.dto.LectureRequestDto;
import com.sparta.adminserver.dto.LectureResponseDto;
import com.sparta.adminserver.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LectureController {


    private final LectureService lectureService;

    @PostMapping("/lecture")
    public LectureResponseDto registerLecture(@RequestBody LectureRequestDto req) {
        return lectureService.registerLecture(req);
    }

    @PutMapping("/lecture/{lectureId}")
    public LectureResponseDto modifyLecture(@PathVariable Long lectureId, @RequestBody LectureRequestDto req) {
        return lectureService.modifyLecture(lectureId, req);
    }

    @GetMapping("/lecture/{lectureId}")
    public LectureResponseDto findLecture(@PathVariable Long lectureId) {
        return lectureService.findLecture(lectureId);
    }

    //@GetMapping("/lectures?tutor-id={tutorId}")
    @GetMapping("/lectures/tutor/{tutorId}")
    public List<LectureResponseDto> findLectureByTutor(@PathVariable Long tutorId) {
        return lectureService.findLectureByTutor(tutorId);
    }

    @GetMapping("/lectures/category/{category}")
    public List<LectureResponseDto> findLectureByCategory(@PathVariable String category) {
        return lectureService.findLectureByCategory(category);
    }
}