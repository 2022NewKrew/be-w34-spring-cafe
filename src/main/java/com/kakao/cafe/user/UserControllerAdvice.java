package com.kakao.cafe.user;

import com.kakao.cafe.user.exception.CustomDuplicateUserException;
import com.kakao.cafe.user.exception.CustomPasswordNotEqualsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(UserControllerAdvice.class);

    @ExceptionHandler(CustomDuplicateUserException.class)
    public ResponseEntity<String> handleCustomDuplicateUserException(
        CustomDuplicateUserException e) {
        logger.info("[ERROR] 중복된 유저 아이디", e.getCause());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(CustomPasswordNotEqualsException.class)
    public String handleCustomPasswordNotEqualsException() {
        logger.info("[ERROR] 패스워드 불일치, 로그인 실패");
        return "user/login_failed";
    }
}
