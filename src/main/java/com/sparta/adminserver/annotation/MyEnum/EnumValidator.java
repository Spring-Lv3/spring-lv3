package com.sparta.adminserver.annotation.MyEnum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j(topic = "MyEnum annotation : ")
public class EnumValidator implements ConstraintValidator<MyEnum, String> {
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(MyEnum constraintAnnotation){
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Enum<?>[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null) {
            log.error("enum Constants is null");
            return false;
        }
        for(Enum<?> enumValue : enumConstants){
            if(enumValue.name().equals(value)){
                return true;
            }
        }
        return false;
    }
}
