//package com.sparta.adminserver.filter;
//
//import com.sparta.adminserver.entity.User;
//import com.sparta.adminserver.jwt.JwtUtil;
//import com.sparta.adminserver.repository.UserRepository;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//
//@Slf4j(topic = "JwtAuthFilter")
//@Component
//@Order(2)
//public class JwtAuthFilter implements Filter {
//
//    private final UserRepository userRepository;
//    private final JwtUtil jwtUtil;
//
//    public JwtAuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String url = httpServletRequest.getRequestURI();
//
//        if (StringUtils.hasText(url) &&
//                (url.startsWith("/api/user") || url.startsWith("/css") || url.startsWith("/js"))
//        ) {
//            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행
//            chain.doFilter(request, response); // 다음 Filter 로 이동
//        } else {
//            // 나머지 API 요청은 인증 처리 진행
//            // 토큰 확인
//            String tokenValue = jwtUtil.getJwtFromHeader(httpServletRequest);
//
//            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
//                // 토큰 검증
//                if (!jwtUtil.validateToken(tokenValue)) {
//                    throw new IllegalArgumentException("Token Error");
//                }
//
//                // 토큰에서 사용자 정보 가져오기
//                Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
//
//                User user = userRepository.findByEmail(info.getSubject()).orElseThrow(() ->
//                        new NullPointerException("Not Found User")
//                );
//
//                request.setAttribute("user", user);
//                chain.doFilter(request, response); // 다음 Filter 로 이동
//            } else {
//                throw new IllegalArgumentException("Not Found Token");
//            }
//        }
//    }
//
//}