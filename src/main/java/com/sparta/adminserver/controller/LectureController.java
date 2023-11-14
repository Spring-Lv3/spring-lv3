package com.sparta.adminserver.controller;

import com.sparta.adminserver.dto.LectureRequestDto;
import com.sparta.adminserver.dto.LectureResponseDto;
import com.sparta.adminserver.dto.TutorRequestDto;
import com.sparta.adminserver.dto.TutorResponseDto;
import com.sparta.adminserver.service.LectureService;
import com.sparta.adminserver.service.TutorService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LectureController {


    private final LectureService lectureService;

    @PostMapping("/lecture")
    public LectureResponseDto registerLecture(@RequestBody LectureRequestDto req) {
        return lectureService.registeredLecture(req);
    }

    @PutMapping("/lecture/{lectureId}")
    public LectureResponseDto modifiedLecture(@PathVariable Long lectureId, @RequestBody LectureRequestDto req){
        return lectureService.modifiedLecture(lectureId, req);
    }

    @GetMapping("/lecture/{lectureId}")
    public LectureResponseDto findLecture(@PathVariable Long lectureId){
        return lectureService.findLecture(lectureId);
    }

    // TODO: 2023-11-14 카테고리별 강의 조회 해야함
}