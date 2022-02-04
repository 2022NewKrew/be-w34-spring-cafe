package com.kakao.cafe.system;

import com.kakao.cafe.exception.NoSuchUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by melodist
 * Date: 2022-02-04 004
 * Time: 오후 2:37
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchUserException.class)
    public String exception() {
        return "/user/login_failed";
    }
}
