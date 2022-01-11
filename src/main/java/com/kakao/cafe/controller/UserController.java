package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/user/form.html")
    public String getSignUp() {

        return "user/form";
    }

    @PostMapping(value = "/user/create")
    public String postSignUp(User user) {
        logger.info("user:{}",user);
        return "redirect:/user/login.html";
    }
}
