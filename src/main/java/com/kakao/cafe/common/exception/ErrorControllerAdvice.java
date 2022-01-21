package com.kakao.cafe.common.exception;

import com.kakao.cafe.common.exception.custom.CustomException;
import com.kakao.cafe.common.exception.custom.LoginFailedException;
import com.kakao.cafe.common.exception.custom.UpdateFailedException;
import com.kakao.cafe.common.exception.custom.UserNotFoundException;
import com.kakao.cafe.common.exception.data.ErrorCode;
import com.kakao.cafe.common.exception.response.ErrorResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(value = { UserNotFoundException.class, UpdateFailedException.class })
    protected String handleCustomException(CustomException e, Model model) {
        model.addAttribute("error", ErrorResponse.of(e.getErrorCode()));
        return "common/error_page";
    }

    @ExceptionHandler(value = { LoginFailedException.class })
    protected String handleLoginFailedException(LoginFailedException e, Model model) {
        model.addAttribute("error", ErrorResponse.of(e.getErrorCode()));
        return "login/login_failed";
    }

    @ExceptionHandler(value = { DataAccessException.class })
    protected String handleDataAccessException(DataAccessException ex, Model model) {
        model.addAttribute("error", ErrorResponse.of(ErrorCode.DATABASE_ERROR));
        return "common/error_page";
    }
}
