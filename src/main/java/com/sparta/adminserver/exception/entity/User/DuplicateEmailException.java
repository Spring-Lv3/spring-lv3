package com.sparta.adminserver.exception.entity.User;

public class DuplicateEmailException extends IllegalArgumentException{
    public DuplicateEmailException() {
        super("이미 가입된 이메일입니다.");
    }
}
