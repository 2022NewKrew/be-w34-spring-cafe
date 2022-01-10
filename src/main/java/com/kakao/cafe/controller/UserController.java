package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    UserController(UserService userService) {
    }

    @GetMapping("user/form")
    public String getSignUp() {
        return "user/form";
    }
}
