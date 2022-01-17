package com.example.kakaocafe.core.aop;

import com.example.kakaocafe.core.exception.PostBusinessException;
import com.example.kakaocafe.core.exception.SignUpException;

import com.example.kakaocafe.core.exception.HasNotPermissionException;
import com.example.kakaocafe.core.meta.URLPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(SignUpException.class)
    public ModelAndView signUpExceptionHandle(HttpServletRequest req, RedirectAttributes redirectAttr, SignUpException ex) {
        logger.error("회원 가입 에러", ex);

        final String email = req.getParameter("email");
        final String name = req.getParameter("name");

        redirectAttr.addFlashAttribute("isFailed", true);
        redirectAttr.addFlashAttribute("email", email);
        redirectAttr.addFlashAttribute("name", name);

        return new ModelAndView(URLPath.SHOW_SIGN_UP_FORM.getRedirectPath());
    }

    @ExceptionHandler(LoginException.class)
    public ModelAndView loginExceptionHandle(LoginException ex, RedirectAttributes redirectAttr) {
        logger.error("로그인 에러", ex);

        redirectAttr.addFlashAttribute("isFailed", true);

        return new ModelAndView(URLPath.SHOW_LOGIN_FORM.getRedirectPath());
    }

    @ExceptionHandler(HasNotPermissionException.class)
    public ModelAndView UnauthorizedExceptionHandle(HasNotPermissionException ex,
                                                    HttpServletRequest request,
                                                    RedirectAttributes redirectAttr) {
        logger.error("권한 없음", ex);
        redirectAttr.addFlashAttribute("errorMsg", ex.getMessage());

        final String redirectUrl = request.getHeader(HttpHeaders.REFERER);
        return new ModelAndView("redirect:" + redirectUrl);
    }

    @ExceptionHandler(PostBusinessException.class)
    public ModelAndView postBusinessExceptionHandle(PostBusinessException ex,
                                                    HttpServletRequest request,
                                                    RedirectAttributes redirectAttr) {
        logger.error("권한 없음", ex);
        redirectAttr.addFlashAttribute("errorMsg", ex.getMessage());

        final String redirectUrl = request.getHeader(HttpHeaders.REFERER);
        return new ModelAndView("redirect:" + redirectUrl);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView noSuchElementExceptionHandle(NoSuchElementException ex) {
        logger.error("존재하지 않는 db 조회 에러", ex);
        return new ModelAndView(URLPath.SHOW_ERROR_404.getRedirectPath());
    }
}
