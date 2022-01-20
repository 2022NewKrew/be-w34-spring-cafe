package com.kakao.cafe.user;

import com.kakao.cafe.user.exception.CustomDuplicateUserException;
import com.kakao.cafe.user.exception.CustomInvalidedSessionException;
import com.kakao.cafe.user.exception.CustomLoginFailException;
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
        logger.info(e.getMessage(), e.getCause());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(CustomLoginFailException.class)
    public String handleCustomPasswordNotEqualsException(CustomLoginFailException e) {
        logger.info(e.getMessage());
        return "user/login_failed";
    }

    @ExceptionHandler(CustomInvalidedSessionException.class)
    public String handleCustomInvalidedSessionException(CustomInvalidedSessionException e) {
        logger.info(e.getMessage());
        return "user/login";
    }
}
