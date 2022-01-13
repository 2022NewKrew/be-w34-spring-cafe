package com.kakao.cafe.exception;

import com.kakao.cafe.user.web.UserController;
import java.security.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackageClasses = UserController.class)
public class UserControllerExceptionHandler {

    // @Valid 검증 실패 시
    @ExceptionHandler(InvalidParameterException.class)
    protected
}
