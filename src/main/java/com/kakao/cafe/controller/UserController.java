package com.kakao.cafe.controller;

import com.kakao.cafe.dto.request.SignupReqDto;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {

        return "/user/login";
    }

    @GetMapping("/users")
    public String getUsers() {
        // show all users
        return "/user/list";
    }

    @GetMapping("/users/signup")
    public String getSignupForm() {
        return "/user/form";
    }

    @PostMapping("/users")
    public String signup(@ModelAttribute SignupReqDto signupReqDto) {
        // signup and redirect
        userService.signup(signupReqDto);
        return "redirect:/users";
    }
}
