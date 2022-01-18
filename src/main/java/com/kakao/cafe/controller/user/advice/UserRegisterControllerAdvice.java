package com.kakao.cafe.controller.user.advice;

import com.kakao.cafe.controller.user.UserRegisterController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = UserRegisterController.class)
public class UserRegisterControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException exception, Model model) {
        model.addAttribute("errorExist", true);
        model.addAttribute("errorMsg", exception.getMessage());
        return "user/form";
    }
}
