package com.kakao.cafe.controller;

import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.domain.auth.AuthLoginDto;
import com.kakao.cafe.service.AuthService;
import com.kakao.cafe.utils.SessionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute AuthLoginDto dto,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/login";
        }
        Auth auth = authService.login(dto);
        session.setAttribute(SessionName.AUTH, auth);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
