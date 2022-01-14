package com.kakao.cafe.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {
    private static final String HEADER_REFERER = "Referer";

    // @Valid 유효성을 통과하지 못하면 BindException 이 발생한다.
    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public String handleInvalidRequestException(BindException e, HttpServletRequest request, RedirectAttributes rttr) {
        String pathOfRedirection = getPathOfRedirectionFromRequest(request);

        Errors errors = e.getBindingResult();
        errors.getFieldErrors().forEach(error -> rttr.addFlashAttribute(error.getField(), error.getDefaultMessage()));

        rttr.addFlashAttribute("msg", e.getMessage());
        return "redirect:" + pathOfRedirection;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request, RedirectAttributes rttr) {
        e.printStackTrace();
        String pathOfRedirection = getPathOfRedirectionFromRequest(request);
        rttr.addFlashAttribute("msg", e.getMessage());
        return "redirect:" + pathOfRedirection;
    }

    private String getPathOfRedirectionFromRequest(HttpServletRequest request) {
        return request.getHeader(HEADER_REFERER);
    }
}
