package com.kakao.cafe.auth.controller;

import com.kakao.cafe.auth.dto.LoginRequest;
import com.kakao.cafe.auth.service.AuthService;
import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/login")
    public String login(LoginRequest loginRequest){
        User user = userService.getOneByUserId(loginRequest.getUserId());
        authService.validatePassword(loginRequest.getPassword(), user.getPassword());
        return "redirect:/";
    }
}
