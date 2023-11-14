package com.sparta.adminserver.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {


    @Autowired
    private final JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(JwtRequired.class) || handlerMethod.getBeanType().isAnnotationPresent(JwtRequired.class)) {
            String token = jwtUtil.getJwtFromHeader(request); // bearer 떼진 token
            if(!jwtUtil.validateToken(token)){
                return false;
            }
            // claim 담기
            Claims claims = jwtUtil.getUserInfoFromToken(token);
            request.setAttribute("user", claims);
        }
        return true;
    }
}
