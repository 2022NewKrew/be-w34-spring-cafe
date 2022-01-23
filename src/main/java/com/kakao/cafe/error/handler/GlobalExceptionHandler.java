package com.kakao.cafe.error.handler;

import com.kakao.cafe.error.ErrorMessage;
import com.kakao.cafe.error.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.kakao.cafe.Constant.MESSAGE_NOT_BLANK;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler({AuthorizationException.class, ArticleNotFoundException.class, InvalidPasswordException.class, UserAlreadyExistException.class, UserNotFoundException.class, ReplyNotFoundException.class})
    public String globalHandler(RuntimeException e, Model model) {
        logger.error("{} {}", e.getClass().getName(), e.getMessage());
        ErrorMessage errorMessage = ErrorMessage.from(e.getMessage());
        model.addAttribute("error",errorMessage);
        return "error";
    }

    @ExceptionHandler(BindException.class)
    public String bindingHandler(BindException e, Model model) {
        logger.error("bindingHandler {} {}", e.getClass().getName(), e.getMessage());
        ErrorMessage errorMessage = ErrorMessage.from(MESSAGE_NOT_BLANK);
        model.addAttribute("error",errorMessage);
        return "error";
    }

    @ExceptionHandler(LoginException.class)
    public String loginHandler(LoginException e, Model model) {
        logger.error("loginHandler {} {}", e.getClass().getName(), e.getMessage());
        return "user/login";
    }
}
