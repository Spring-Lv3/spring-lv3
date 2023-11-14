package com.sparta.adminserver.exception.entity.User;

public class ManagerRoleNotAvailableException extends RuntimeException {
    public ManagerRoleNotAvailableException() {
        super("매니저 권한은 커리큘럼 부서 혹은 개발 부서만 받을 수 있습니다.");
    }
}
