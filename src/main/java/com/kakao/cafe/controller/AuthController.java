package com.kakao.cafe.controller;

import com.kakao.cafe.controller.interceptor.LoginRequired;
import com.kakao.cafe.dto.AuthDTO;
import com.kakao.cafe.dto.UserResponseDTO;
import com.kakao.cafe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

import static com.kakao.cafe.Constant.SESSION_USER;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@ModelAttribute @Validated AuthDTO authDto, BindingResult bindingResult, HttpSession session) {
        logger.info("login {} {}", authDto.getUserId(), authDto.getPassword());
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(fieldError -> logger.error("Field : {}, Message : {}", fieldError.getField(), fieldError.getDefaultMessage()));
            return "redirect:/login-failed";
        }
        UserResponseDTO userResponseDTO = authService.login(authDto);
        session.setAttribute(SESSION_USER, userResponseDTO);
        return "redirect:/";
    }

    @LoginRequired
    @DeleteMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
