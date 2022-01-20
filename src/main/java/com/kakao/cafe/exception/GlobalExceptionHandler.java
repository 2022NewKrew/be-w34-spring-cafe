package com.kakao.cafe.exception;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String errorView = "errors/error";

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException e, HttpServletRequest request, HttpServletResponse response, Model model) {
        ErrorCode errorCode = e.getErrorCode();
        logger.error("CustomException status: {}", errorCode.getStatus());
        logger.error("CustomException message: {}", errorCode.getMessage());

        response.setStatus(errorCode.getStatus());

        model.addAttribute("message", errorCode.getMessage());
        model.addAttribute("referer", request.getHeader("referer"));

        return errorView;
    }

    @ExceptionHandler(BindException.class)
    public String handleBindException(BindException e, HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.error("BindException message: {}", e.getMessage());

        response.setStatus(ErrorCode.INVALID_INPUT.getStatus());

        String message = e.getAllErrors().stream()
                .map(err -> err.getDefaultMessage() + System.lineSeparator())
                .collect(Collectors.joining());
        model.addAttribute("message", message);
        model.addAttribute("referer", request.getHeader("referer"));

        return errorView;
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request, HttpServletResponse response, Model model) {
        logger.error("Exception message: {}", e.getMessage());

        response.setStatus(ErrorCode.INTER_SERVER_ERROR.getStatus());

        model.addAttribute("message", ErrorCode.INTER_SERVER_ERROR.getMessage());
        model.addAttribute("referer", request.getHeader("referer"));

        return errorView;
    }
}
