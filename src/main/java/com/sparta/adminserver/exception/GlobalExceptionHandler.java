package com.sparta.adminserver.exception;


import com.sparta.adminserver.exception.entity.Tutor.TutorNotFoundException;
import com.sparta.adminserver.exception.entity.User.*;
import com.sparta.adminserver.exception.entity.lecture.LectureNotFoundException;
import com.sparta.adminserver.exception.jwt.JwtTokenNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(JwtTokenNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleJwtTokenNotFoundException(){
        // 토큰 미식별로 다시 로그인 페이지 가는 로직
        return new ResponseEntity<>("토큰 미식별로 다시 로그인 페이지 가는 로직", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotAllowedForNonManagerException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleNotAllowedForNonManagerException(){
        return new ResponseEntity<>("매니저 권한이 없습니다.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LectureNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 404
    public ResponseEntity<?> handleLectureNotFoundException(){
        return new ResponseEntity<>("해당 강의가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TutorNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 404
    public ResponseEntity<?> handleTutorNotFoundException(){
        return new ResponseEntity<>("해당 강사가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 404
    public ResponseEntity<?> handleDuplicateEmailException(){
        return new ResponseEntity<>("이미 가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 404
    public ResponseEntity<?> handleEmailNotFoundException(){
        return new ResponseEntity<>("가입되지 않은 이메일입니다.", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ManagerRoleNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 404
    public ResponseEntity<?> handleManagerRoleNotAvailableException(){
        return new ResponseEntity<>("매니저 권한은 커리큘럼 부서 혹은 개발 부서만 받을 수 있습니다.", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PasswordNotAvailableByRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 404
    public ResponseEntity<?> handlePasswordNotAvailableByRuleException(){
        return new ResponseEntity<>("비밀번호는 8자 이상 15자 이하로 영문, 숫자, 특수문자를 포함해야합니다.", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PasswordNotMatchedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 404
    public ResponseEntity<?> handlePasswordNotMatchedException(){
        return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
    }
}
