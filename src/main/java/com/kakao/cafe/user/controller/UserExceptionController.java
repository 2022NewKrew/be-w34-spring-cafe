package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.exception.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionController {
    private static final Map<Class<? extends UserException>, String> ERROR_MESSAGE_MAP = Map.ofEntries(
            Map.entry(InvalidUserIdException.class, "잘못된 사용자 아이디 형식입니다."),
            Map.entry(InvalidPasswordException.class, "잘못된 비밀번호 형식입니다."),
            Map.entry(InvalidNameException.class, "잘못된 이름 형식입니다."),
            Map.entry(InvalidEmailException.class, "잘못된 이메일 형식입니다.")
    );

    @ExceptionHandler(value = UserException.class)
    public String invalidUserId(UserException exception, Model model) {
        model.addAttribute("errorMsg", ERROR_MESSAGE_MAP.get(exception.getClass()));
        return "error";
    }
}
