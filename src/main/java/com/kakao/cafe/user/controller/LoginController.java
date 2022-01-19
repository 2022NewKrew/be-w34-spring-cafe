package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.Password;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.user.dto.LoginFormDto;
import com.kakao.cafe.user.service.UserService;
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
