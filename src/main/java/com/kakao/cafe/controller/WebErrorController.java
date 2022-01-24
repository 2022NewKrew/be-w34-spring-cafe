package com.kakao.cafe.controller;

import com.kakao.cafe.util.exception.ForbiddenException;
import com.kakao.cafe.util.exception.NotFoundException;
import com.kakao.cafe.util.exception.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Controller
@Slf4j
public class WebErrorController implements ErrorController {
    @ExceptionHandler(IllegalArgumentException.class)
    public String argsExceptionHandle(Exception e, Model model, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        model.addAttribute("errorMessage", e.getMessage());

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return "error";
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public String unAuthExceptionHandle(Exception e, Model model, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        model.addAttribute("errorMessage", e.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "error";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFoundExceptionHandle(Exception e, Model model, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        model.addAttribute("errorMessage", e.getMessage());

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "error";
    }

    @ExceptionHandler(ForbiddenException.class)
    public String forbiddenExceptionHandle(Exception e, Model model, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        model.addAttribute("errorMessage", e.getMessage());

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String globalExceptionHandle(Exception e, Model model, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        model.addAttribute("errorMessage", "페이지를 찾을 수 없습니다.");

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return "error";
    }

    @ExceptionHandler(BindException.class)
    public String validateException(Errors errors, RedirectAttributes attr, HttpServletRequest request) {
        errors.getFieldErrors().forEach(err -> attr.addFlashAttribute(err.getField(), err.getDefaultMessage()));
        return "redirect:" + request.getHeader("Referer");
    }


    @RequestMapping("/error")
    public String errorPage(Model model, Exception e) {
        log.error(e.getMessage(), e);
        model.addAttribute("errorMessage", "페이지를 찾을 수 없습니다.");
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
