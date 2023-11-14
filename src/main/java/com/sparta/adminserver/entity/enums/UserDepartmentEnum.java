package com.sparta.adminserver.entity.enums;

import lombok.Getter;

@Getter
public enum UserDepartmentEnum {

    CURRICULUM(Department.curriculum),
    MARKETING(Department.marketing),
    DEVELOPMENT(Department.development);

    private final String value;

    UserDepartmentEnum(String value) {
        this.value = value;
    }
    public String value(){ return value;}

    public static class Department{

        public static final String curriculum = "DEPARTMENT_CURRICULUM";
        public static final String marketing = "DEPARTMENT_MARKETING";
        public static final String development = "DEPARTMENT_DEVELOPMENT";
    }

}
