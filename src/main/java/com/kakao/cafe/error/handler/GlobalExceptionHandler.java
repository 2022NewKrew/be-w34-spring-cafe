package com.kakao.cafe.error.handler;

import com.kakao.cafe.error.ErrorMessage;
import com.kakao.cafe.error.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({AuthorizationException.class, ArticleNotFoundException.class, InvalidPasswordException.class, UserAlreadyExistException.class, UserNotFoundException.class})
    public String globalHandler(RuntimeException e, Model model) {
        logger.error("{} {}", e.getClass().getName(), e.getMessage());
        ErrorMessage errorMessage = ErrorMessage.from(e.getMessage());
        model.addAttribute("error",errorMessage);
        return "error";
    }
}
