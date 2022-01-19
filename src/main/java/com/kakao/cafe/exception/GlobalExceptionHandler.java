package com.kakao.cafe.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException e, HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.error("CustomException status: {}", e.getErrorCode().getStatus());
        logger.error("CustomException errorCode: {}", e.getErrorCode().getErrorCode());
        logger.error("CustomException message: {}", e.getErrorCode().getMessage());

        response.setStatus(e.getErrorCode().getStatus());

        model.addAttribute("message", e.getErrorCode().getMessage());
        model.addAttribute("referer", request.getHeader("referer"));

        return "errors/error";
    }

    @ExceptionHandler({LoginUserNotFoundException.class, LoginWrongPasswordException.class})
    public String handleLoginException(Exception e, Model model) {
        logger.error("LoginException message: {}", e.getMessage());

        model.addAttribute("message", e.getMessage());

        return "users/login";
    }
}
