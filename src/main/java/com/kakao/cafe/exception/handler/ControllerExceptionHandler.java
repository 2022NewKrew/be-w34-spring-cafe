package com.kakao.cafe.exception.handler;

import com.kakao.cafe.exception.NoSuchPostException;
import com.kakao.cafe.exception.NoSuchUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NoSuchUserException.class)
    public String noSuchUser(Exception e, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("errorMessage", "해당되는 유저가 없습니다");
        return "redirect:/error";
    }

    @ExceptionHandler(NoSuchPostException.class)
    public String noSuchPost(Exception e, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("errorMessage", "해당되는 게시물이 없습니다");
        return "redirect:/error";
    }

    @ExceptionHandler(Exception.class)
    public String otherwise(Exception e, RedirectAttributes redirectAttrs) {
        // XXX: Not reaching here
        redirectAttrs.addFlashAttribute("errorMessage", "Something went wrong...");
        return "redirect:/error";
    }
}
