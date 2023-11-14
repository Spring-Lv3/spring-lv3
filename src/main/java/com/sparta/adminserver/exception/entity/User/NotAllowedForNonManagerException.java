package com.sparta.adminserver.exception.entity.User;

public class NotAllowedForNonManagerException extends RuntimeException{
    public NotAllowedForNonManagerException() {
        super("매니저 권한이 없습니다.");
    }
}
