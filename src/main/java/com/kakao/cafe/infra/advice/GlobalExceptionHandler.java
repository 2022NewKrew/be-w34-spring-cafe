package com.kakao.cafe.infra.advice;

import com.kakao.cafe.common.dto.ErrorResponse;
import com.kakao.cafe.common.exception.BadRequestException;
import com.kakao.cafe.common.exception.ForbiddenException;
import com.kakao.cafe.common.exception.NotFoundException;
import com.kakao.cafe.common.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_VIEW_NAME = "errors/error";
    private static final String MESSAGE = "message";
    private final ModelAndView mv = new ModelAndView(ERROR_VIEW_NAME);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView notFoundException(NotFoundException e) {
        log.error("[NotFoundException] - {}", e.getMessage());
        mv.addObject(MESSAGE, ErrorResponse.of(e.getMessage()));
        return mv;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorizedException(UnauthorizedException e) {
        log.error("[UnauthorizedException] - {}", e.getMessage());
        return "/user/login-form";
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView forbiddenException(ForbiddenException e) {
        log.error("[ForbiddenException] - {}", e.getMessage());
        mv.addObject(MESSAGE, ErrorResponse.of(e.getMessage()));
        return mv;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView badRequestException(BadRequestException e) {
        log.error("[BadRequestException] - {}", e.getMessage());
        mv.addObject(MESSAGE, ErrorResponse.of(e.getMessage()));
        return mv;
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ModelAndView bindException(BindException e) {
        log.error("[BindException] - {}", e.getMessage());
        mv.addObject(MESSAGE, ErrorResponse.of(e.getMessage()));
        return mv;
    }
}
