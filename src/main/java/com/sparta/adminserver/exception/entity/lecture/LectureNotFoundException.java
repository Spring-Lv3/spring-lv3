package com.sparta.adminserver.exception.entity.lecture;

public class LectureNotFoundException extends RuntimeException {
    public LectureNotFoundException() {
        super("해당 강의가 존재하지 않습니다.");
    }
}
