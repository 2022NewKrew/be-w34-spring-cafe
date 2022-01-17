package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserAuthController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    @PostMapping("/login")
    public String login(@ModelAttribute UserAuthDto userAuthDto, HttpSession httpSession) {
        UserDto user = userService.login(userAuthDto);
        httpSession.setAttribute("user", user);
        return "redirect:/users";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
