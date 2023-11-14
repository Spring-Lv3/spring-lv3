package com.sparta.adminserver.entity.enums;

public enum ManagerRoleEnum {
    MANAGER("MANAGER"),  // 사용자 권한
    STAFF("STAFF");  // 관리자 권한

    private final String value;

    ManagerRoleEnum(String value) {
        this.value = value;
    }
    public String value(){ return value;}

    public static class Role {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_STAFF";
    }
}