package com.kakao.cafe.common.exception;

import com.kakao.cafe.common.exception.custom.CustomException;
import com.kakao.cafe.common.exception.custom.LoginFailedException;
import com.kakao.cafe.common.exception.custom.UpdateFailedException;
import com.kakao.cafe.common.exception.custom.UserNotFoundException;
import com.kakao.cafe.common.exception.response.ErrorResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(value = { UserNotFoundException.class, LoginFailedException.class, UpdateFailedException.class })
    protected String handleUserNotFoundException(CustomException e, Model model) {
        model.addAttribute("error", ErrorResponse.of(e.getErrorCode()));
        return "common/error_page";
    }
}
