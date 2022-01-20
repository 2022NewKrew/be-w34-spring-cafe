package com.kakao.cafe.infra.exception;

import com.kakao.cafe.module.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ExceptionHandler({
            DuplicateUserException.class,
            NoSuchDataException.class,
            ForbiddenException.class,
            DeleteRuleException.class
    })
    public String handleRuntimeExceptions(final CustomRuntimeException e, Model model, HttpServletResponse response) {
        response.setStatus(e.getErrorCode().getValue());
        model.addAttribute("msg", errorMsg(e.getName(), e.getMessage()));
        logger.error(e.getName());
        return "infra/error";
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            DataIntegrityViolationException.class
    })
    public String handleIllegalArgumentException(final Exception e, Model model, HttpServletResponse response) {
        response.setStatus(ErrorCode.BAD_REQUEST.getValue());
        model.addAttribute("msg", errorMsg(e.getClass().getSimpleName(), e.getMessage()));
        logger.error(e.getClass().getSimpleName());
        return "infra/error";
    }

    @ExceptionHandler(HttpSessionRequiredException.class)
    public String handleHttpSessionRequiredException(final HttpSessionRequiredException e, Model model, HttpServletResponse response) {
        response.setStatus(ErrorCode.UNAUTHORIZED.getValue());
        model.addAttribute("msg", errorMsg("HttpSessionRequiredException", e.getMessage()));
        logger.error("HttpSessionRequiredException");
        return "user/login";
    }

    private String errorMsg(String name, String msg) {
        return name + " : " + msg;
    }
}
