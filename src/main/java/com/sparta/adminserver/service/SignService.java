package com.sparta.adminserver.service;

import com.sparta.adminserver.dto.SignInRequestDto;
import com.sparta.adminserver.dto.SignUpRequestDto;
import com.sparta.adminserver.exception.entity.User.*;
import com.sparta.adminserver.entity.User;
import com.sparta.adminserver.entity.enums.ManagerRoleEnum;
import com.sparta.adminserver.entity.enums.UserDepartmentEnum;
import com.sparta.adminserver.jwt.JwtUtil;
import com.sparta.adminserver.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SignService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // (?=.*[규칙] : 규칙의 문자열을 하나 이상 포함
    // ^ 시작, $ 끝
    // ()()().{min,max} 글자수
    private final String passwordRegex = "^(?=.*[a-zA-z])(?=.*\\d)(?=.*[!@#$%^&*()-_=+]).{8,15}$";

    public void signup(SignUpRequestDto req) {
        // 이메일 중복
        String email = req.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicateEmailException();
        }

        // 권한 및 부서 검사
        if (Objects.equals(req.getRole(), ManagerRoleEnum.MANAGER.toString())) {
            if (Stream.of(UserDepartmentEnum.CURRICULUM, UserDepartmentEnum.DEVELOPMENT).noneMatch((userDepartmentEnum -> userDepartmentEnum.toString().equals(req.getDepartment())))) {
                throw new ManagerRoleNotAvailableException();
            }
        }

        // 비밀번호 유효 검사
        String password = req.getPassword();
        if (!password.matches(passwordRegex)) {
            throw new PasswordNotAvailableByRuleException();
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);
        User user = req.toEntity();
        user.setPassword(encodedPassword);

        //저장
        userRepository.save(user);
    }

    public void signin(SignInRequestDto req, HttpServletResponse res) {
        String email = req.getEmail();
        String password = req.getPassword();

        // email 존재 검증
        User user = userRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotMatchedException();
        }

        // jwt 생성, 저장, 쿠키 추가
        String token = jwtUtil.createToken(email, ManagerRoleEnum.valueOf(user.getRole()));
        jwtUtil.addJwtToHeader(token, res); // 서블릿 리스폰스를 주면 안에서 토큰을 넣어준다.
    }
}
