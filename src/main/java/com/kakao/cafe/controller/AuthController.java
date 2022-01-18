package com.kakao.cafe.controller;

import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.dto.auth.AuthRequest;
import com.kakao.cafe.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(AuthRequest authRequest, HttpSession session) {
        Auth auth = authService.login(authRequest.getUsername(), authRequest.getPassword());
        session.setAttribute("auth", auth);

        return "redirect:/";
    }

    @DeleteMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
