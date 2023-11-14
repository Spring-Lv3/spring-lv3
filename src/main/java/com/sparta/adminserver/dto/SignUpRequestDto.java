package com.sparta.adminserver.dto;

import com.sparta.adminserver.annotation.MyEnum.MyEnum;
import com.sparta.adminserver.entity.User;
import com.sparta.adminserver.entity.enums.ManagerRoleEnum;
import com.sparta.adminserver.entity.enums.UserDepartmentEnum;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequestDto {
    @Email
    private String email;
    private String password;
    @MyEnum(enumClass = ManagerRoleEnum.class)
    private String role;
    @MyEnum(enumClass = UserDepartmentEnum.class)
    private String department;

    public User toEntity() {
        User user = new User();
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setRole(this.role);
        user.setDepartment(this.department);
        return user;
    }
}
