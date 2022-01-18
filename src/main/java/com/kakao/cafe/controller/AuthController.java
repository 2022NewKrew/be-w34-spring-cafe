package com.kakao.cafe.controller;

import com.kakao.cafe.dto.LoginDTO;
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

@Controller
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private static final String SESSION_USER = "sessionUser";
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@ModelAttribute @Validated LoginDTO loginDTO, BindingResult bindingResult, HttpSession session) {
        logger.error("login {} {}", loginDTO.getUserId(), loginDTO.getPassword());
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(fieldError -> logger.error("Field : {}, Message : {}", fieldError.getField(), fieldError.getDefaultMessage()));
            return "redirect:/login-failed";
        }
        UserResponseDTO userResponseDTO = authService.login(loginDTO);
        session.setAttribute(SESSION_USER, userResponseDTO.getUserId());
        return "redirect:/";
    }

    @DeleteMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
