package com.sparta.adminserver.annotation.MyEnum;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

// Custom Contraint Annotation 생성시 message, groups, payload 정의
@Documented // 문서
@Target(ElementType.FIELD) // 여기를 타겟으로
@Retention(RetentionPolicy.RUNTIME) // 런타임까지 유지
@Constraint(validatedBy = {EnumValidator.class}) //
public @interface MyEnum {
    // 오류 메세지 관리
    String message() default "선택할 수 없는 값 입니다.";
    // 상황별 validattion 제어
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default{};
    // 제약할 Enum
    Class<? extends Enum<?>> enumClass();
}
