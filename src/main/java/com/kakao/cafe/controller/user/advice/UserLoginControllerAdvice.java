package com.kakao.cafe.controller.user.advice;

import com.kakao.cafe.controller.user.UserLoginController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {UserLoginController.class})
public class UserLoginControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException exception, Model model) {
        model.addAttribute("errorExist", true);
        model.addAttribute("errorMsg", exception.getMessage());
        return "user/login";
    }
}
