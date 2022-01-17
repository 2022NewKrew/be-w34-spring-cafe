package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/auth")
@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public String login(UserDto.LoginDto loginDto, HttpSession session) {
        UserDto.UserSessionDto user = userService.authenticateUser(loginDto);
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    @GetMapping("/login/form")
    public String loginForm() {
        return "user/login";
    }

    @GetMapping("/login/failform")
    public String loginFailForm() {
        return "user/login_failed";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionedUser");
        return "redirect:/";
    }

    @GetMapping("/signup/form")
    public String makeUserHtml() {
        return "user/form";
    }

    @PostMapping("/signup")
    public String makeUser(@ModelAttribute("user") UserDto.CreateUserProfileRequest createUserProfileRequest) {
        userService.createUser(createUserProfileRequest);
        return "redirect:/auth/login/form";
    }
}
