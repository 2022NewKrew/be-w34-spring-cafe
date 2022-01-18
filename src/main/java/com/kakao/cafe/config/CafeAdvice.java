package com.kakao.cafe.config;

import com.kakao.cafe.exception.user.*;
import com.kakao.cafe.exception.article.ArticleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice
public class CafeAdvice {

    @ExceptionHandler(LoginFailedException.class)
    public String handleLoginFailedException(RedirectAttributes redirectAttributes, Exception e) {
        log.info("Login failed");
        log.info(e.getMessage());
        redirectAttributes.addFlashAttribute("message", "로그인에 실패하였습니다. 아이디와 비밀번호를 확인해 주세요.");
        return "redirect:/accounts/login";
    }

    @ExceptionHandler(EditAccountFailedException.class)
    public String handleEditAccountFailedException(RedirectAttributes redirectAttributes, Exception e) {
        log.info("Edit account failed");
        log.info(e.getMessage());
        redirectAttributes.addFlashAttribute("message", "기존 비밀번호가 일치하지 않습니다.");
        return "redirect:/accounts/mypage/edit";
    }

    @ExceptionHandler(PasswordConfirmFailedException.class)
    public String handlePasswordConfirmFailedException(RedirectAttributes redirectAttributes, Exception e) {
        log.info("Password confirm failed");
        log.info(e.getMessage());
        redirectAttributes.addFlashAttribute("message", "새로운 비밀번호와 비밀번호 확인 값이 일치하지 않습니다.");
        return "redirect:/accounts/mypage/edit";
    }

    @ExceptionHandler(UserRegisterFailedException.class)
    public String handleUserRegisterFailedException(Exception e) {
        log.info("User register failed");
        log.info(e.getMessage());
        return "error/500";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(Exception e) {
        log.info("User not found");
        log.info(e.getMessage());
        return "error/500";
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public String handleArticleNotFoundException(Exception e) {
        log.info("Article not found");
        log.info(e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("Exception");
        log.error("[" + e.getClass() + "] " + e.getMessage());
        return "error/404";
    }
}
