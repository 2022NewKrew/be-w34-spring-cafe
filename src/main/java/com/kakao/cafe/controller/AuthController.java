package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserLoginDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(UserLoginDto userLoginDto, HttpSession httpSession) {
        User loginUser = authService.login(userLoginDto);
        httpSession.setAttribute("auth", loginUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
