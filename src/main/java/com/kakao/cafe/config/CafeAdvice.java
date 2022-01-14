package com.kakao.cafe.config;

import com.kakao.cafe.exception.user.LoginFailedException;
import com.kakao.cafe.exception.post.PostNotFoundException;
import com.kakao.cafe.exception.user.UserNotFoundException;
import com.kakao.cafe.exception.user.UserRegisterFailedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CafeAdvice {

    @ExceptionHandler(LoginFailedException.class)
    public String handleLoginFailedException() {
        return "user/login";
    }

    @ExceptionHandler(UserRegisterFailedException.class)
    public String handleUserRegisterFailedException() {
        return "error/500";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException() {
        return "error/500";
    }

    @ExceptionHandler(PostNotFoundException.class)
    public String handlePostNotFoundException() {
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleMethodArgumentTypeMismatchException() {
        return "error/404";
    }
}
