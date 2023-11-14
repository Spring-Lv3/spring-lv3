package com.sparta.adminserver.config.swagger;

import com.sparta.adminserver.jwt.JwtInterceptor;
import com.sparta.adminserver.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private final JwtUtil jwtUtil;
    @Value("${spring.url}")
    private static String URI;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor())
                .excludePathPatterns(URI + "/swagger-resources/**", URI + "/swagger-ui/**", URI + "/v3/api-docs", URI + "/api-docs/**")
                .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs", "/api-docs/**")
                .excludePathPatterns("/signUp", "/signIn", "/error/**", "/reissue")
                .addPathPatterns("/**");
    }

    @Bean
    public JwtInterceptor jwtTokenInterceptor(){
        return new JwtInterceptor(jwtUtil);
    }
}