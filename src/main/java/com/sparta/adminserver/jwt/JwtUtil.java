package com.sparta.adminserver.jwt;

import com.sparta.adminserver.entity.enums.ManagerRoleEnum;
import com.sparta.adminserver.exception.jwt.JwtTokenNotFoundException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;


@Slf4j(topic = "jwt")
@Component
public class JwtUtil {
    // Header KEY 값
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // 사용자 권한 값의 KEY
    public static final String AUTHORIZATION_KEY = "auth";
    // Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";
    // 토큰 만료시간
    private final long TOKEN_TIME = 60 * 60 * 1000L; // 60분
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    //apploication-secret의 property 읽어오기
    @Value("${jwt.secret.key}") // Base64 Encode 한 SecretKey
    private String secretKey;
    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    // 토큰 생성
    public String createToken(String email, ManagerRoleEnum role) {
        Date date = new Date();
        return BEARER_PREFIX + // 헤더
                Jwts.builder()
                        .setSubject(email) // payload, 이름, role, exp, iat
                        .claim(AUTHORIZATION_KEY, role)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm) // signature
                        .compact();
    }

    // JWT Cookie 에 저장
    public void addJwtToHeader(String token, HttpServletResponse res) {
        res.addHeader(AUTHORIZATION_HEADER, token);
    }

    // bearer 제거
    public String getJwtFromHeader(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        log.error("Not Found Token");
        throw new JwtTokenNotFoundException();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error(("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다."));
        } catch (ExpiredJwtException e) {
            log.error(("Expired JWT token, 만료된 JWT token 입니다."));
        } catch (UnsupportedJwtException e) {
            log.error(("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다."));
        } catch (IllegalArgumentException e) {
            log.error(("JWT claims is empty, 잘못된 JWT 토큰 입니다."));
        }
        return false;
    }

    // 토큰에서 사용자 정보 가져오기 // payload 부분만 빼오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // http 요청안의 jwt claim auth값을 확인하는 함수
    // 현재는 Manager 확인 용도 밖에 없음
    public boolean checkAuth(HttpServletRequest req, ManagerRoleEnum managerRoleEnum) {
        Claims claims = ((Claims) req.getAttribute("user"));
        return claims.get("auth").equals(managerRoleEnum.toString());
    }
}
