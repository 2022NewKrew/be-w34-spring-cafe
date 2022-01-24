package com.kakao.cafe.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request, RedirectAttributes attr) {
        log.error(e.getMessage(), e);
        attr.addFlashAttribute("errorMsg", e.getMessage());
        String prevPageUrl = request.getHeader("Referer");
        return "redirect:" + prevPageUrl;
    }
}
