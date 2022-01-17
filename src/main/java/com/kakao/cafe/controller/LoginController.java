package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userService.findUser(userId);
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }
}
