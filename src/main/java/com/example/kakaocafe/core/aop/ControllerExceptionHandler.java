package com.example.kakaocafe.core.aop;

import com.example.kakaocafe.core.exception.SignUpException;
import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.core.meta.ViewPath;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(SignUpException.class)
    public ModelAndView signUpExceptionHandle(HttpServletRequest req, RedirectAttributes redirectAttr, SignUpException ex) {
        ex.printStackTrace();

        final String email = req.getParameter("email");
        final String name = req.getParameter("name");

        redirectAttr.addFlashAttribute("isFailed", true);
        redirectAttr.addFlashAttribute("email", email);
        redirectAttr.addFlashAttribute("name", name);

        return new ModelAndView(URLPath.SHOW_SIGN_UP_FORM.getRedirectPath());
    }

    @ExceptionHandler(LoginException.class)
    public ModelAndView loginExceptionHandle(LoginException ex, RedirectAttributes redirectAttr) {
        ex.printStackTrace();

        redirectAttr.addFlashAttribute("isFailed", true);

        return new ModelAndView(URLPath.SHOW_LOGIN_FORM.getRedirectPath());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ModelAndView noSuchElementExceptionHandle(NoSuchElementException ex) {
        ex.printStackTrace();
        return new ModelAndView(URLPath.SHOW_ERROR_404.getRedirectPath());
    }
}
