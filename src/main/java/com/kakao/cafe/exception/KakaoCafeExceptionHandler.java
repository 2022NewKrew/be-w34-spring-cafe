package com.kakao.cafe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class KakaoCafeExceptionHandler {

    @ExceptionHandler({IllegalStateException.class, NoSuchElementException.class,
            AuthenticationException.class})
    public Object redirectToIndexException(Exception e, Model model) {
        log.info(e.getMessage());
        e.printStackTrace();
        model.addAttribute("error", e.getMessage());
        return "/error";
    }

    @ExceptionHandler(LoginException.class)
    public Object loginError(Exception e) {
        log.info(e.getMessage());
        e.printStackTrace();
        return "redirect:/users/login_failed";
    }

    @ExceptionHandler(Exception.class)
    public Object elseError(Exception e, Model model) {
        log.info(e.getMessage());
        e.printStackTrace();
        model.addAttribute("error", e.getMessage());
        return "/error";
    }
}
