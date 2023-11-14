package com.sparta.adminserver.entity.enums;

import lombok.Getter;

@Getter
public enum LectureCategoryEnum {

    SPRING(Category.spring),
    REACT(Category.react),
    NODE(Category.node);

    private final String value;

    LectureCategoryEnum(String value) {
        this.value = value;
    }
    public String value(){
        return value;
    }

    public static class Category{
        public static final String spring = "CATEGORY_SPRING";
        public static final String react = "CATEGORY_REACT";
        public static final String node = "CATEGORY_NODE";
    }
}
