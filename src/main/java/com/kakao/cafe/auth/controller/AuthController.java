package com.kakao.cafe.auth.controller;

import com.kakao.cafe.auth.dto.LoginRequest;
import com.kakao.cafe.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(LoginRequest loginRequest){
        authService.login(loginRequest);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(){
        authService.logout();
        return "redirect:/";
    }
}
