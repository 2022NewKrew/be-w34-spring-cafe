package com.kakao.cafe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String HEADER_REFERER = "Referer";
    private static final String PREFIX_FOR_REDIRECTION = "redirect:";
    private static final String ROOT_PATH = "/";

    // @Valid 유효성을 통과하지 못하면 BindException 이 발생한다.
    @ExceptionHandler(BindException.class)
    public String handleInvalidRequestException(BindException e, HttpServletRequest request, RedirectAttributes rttr) {
        Errors errors = e.getBindingResult();
        errors.getFieldErrors().forEach(error -> rttr.addFlashAttribute(error.getField(), error.getDefaultMessage()));
        rttr.addFlashAttribute("msg", "잘못된 입력 값입니다.");
        return PREFIX_FOR_REDIRECTION + getPathOfRedirectionFromRequest(request);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request, RedirectAttributes rttr) {
        e.printStackTrace();
        rttr.addFlashAttribute("msg", e.getMessage());
        return PREFIX_FOR_REDIRECTION + getPathOfRedirectionFromRequest(request);
    }

    private String getPathOfRedirectionFromRequest(HttpServletRequest request) {
        String path = request.getHeader(HEADER_REFERER);
        return StringUtils.hasLength(path) ? path : ROOT_PATH;
    }
}
