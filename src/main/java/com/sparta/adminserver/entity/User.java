package com.sparta.adminserver.entity;

import com.sparta.adminserver.entity.enums.ManagerRoleEnum;
import com.sparta.adminserver.entity.enums.UserDepartmentEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String department;
    @Column(nullable = false)
    private String role;
}



