package com.kakao.cafe.common.aop;

import com.kakao.cafe.common.exception.ArticleUpdateException;
import com.kakao.cafe.common.exception.DeletionException;
import com.kakao.cafe.common.exception.SignUpException;
import com.kakao.cafe.common.exception.UserUpdateException;
import com.kakao.cafe.common.meta.URLPath;
import com.kakao.cafe.user.application.port.in.UserRegistrationCommand;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.auth.login.LoginException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public String loginExceptionHandle(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("isFailed", true);
        return URLPath.LOGIN_FAILED.getRedirectPath();
    }

    @ExceptionHandler(SignUpException.class)
    public String signUpExceptionHandle(@ModelAttribute UserRegistrationCommand userRegistrationCommand,
                                        RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("name", userRegistrationCommand.getName());
        redirectAttributes.addFlashAttribute("email", userRegistrationCommand.getEmail());
        redirectAttributes.addFlashAttribute("isFailed", true);

        return URLPath.SHOW_SIGN_UP_FORM.getRedirectPath();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchElementExceptionHandle(NoSuchElementException ex) {
        return URLPath.SHOW_ERROR_404.getRedirectPath();
    }

    @ExceptionHandler(ArticleUpdateException.class)
    public String articleUpdateExceptionHandle() {
        return URLPath.INDEX.getRedirectPath();
    }

    @ExceptionHandler(DeletionException.class)
    public String articleDeletionExceptionHandle() {
        return URLPath.INDEX.getRedirectPath();
    }

    @ExceptionHandler(UserUpdateException.class)
    public String userUpdateExceptionHandle() {
        return URLPath.INDEX.getRedirectPath();
    }
}
