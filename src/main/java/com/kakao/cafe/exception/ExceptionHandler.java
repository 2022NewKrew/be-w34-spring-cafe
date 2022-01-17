package com.kakao.cafe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
    private static final String HEADER_REFERER = "Referer";
    private static final String PREFIX_FOR_REDIRECTION = "redirect:";
    private static final String ROOT_PATH = "/";

    // @Valid 유효성을 통과하지 못하면 BindException 이 발생한다.
    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public String handleInvalidRequestException(BindException e, HttpServletRequest request, RedirectAttributes rttr) {
        String pathOfRedirection = getPathOfRedirectionFromRequest(request);

        Errors errors = e.getBindingResult();
        errors.getFieldErrors().forEach(error -> rttr.addFlashAttribute(error.getField(), error.getDefaultMessage()));

        rttr.addFlashAttribute("msg", e.getMessage());
        return PREFIX_FOR_REDIRECTION + pathOfRedirection;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request, RedirectAttributes rttr) {
        e.printStackTrace();
        String pathOfRedirection = getPathOfRedirectionFromRequest(request);
        rttr.addFlashAttribute("msg", e.getMessage());
        return PREFIX_FOR_REDIRECTION + (StringUtils.hasLength(pathOfRedirection) ? pathOfRedirection : ROOT_PATH);
    }

    private String getPathOfRedirectionFromRequest(HttpServletRequest request) {
        return request.getHeader(HEADER_REFERER);
    }
}
