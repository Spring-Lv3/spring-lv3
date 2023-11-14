package com.sparta.adminserver.exception.entity.Tutor;

public class TutorNotFoundException extends RuntimeException {
    public TutorNotFoundException() {
        super("해당 강사가 존재하지 않습니다.");
    }
}
