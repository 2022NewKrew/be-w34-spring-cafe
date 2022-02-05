package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Password;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.dto.LoginFormDto;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    final private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginFormDto loginFormDto, HttpSession session) {
        UserId userId = new UserId(loginFormDto.getUserId());
        Password password = new Password(loginFormDto.getPassword());
        User user = userService.login(userId, password);
        session.setAttribute("loginUser", user.getUserId());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
