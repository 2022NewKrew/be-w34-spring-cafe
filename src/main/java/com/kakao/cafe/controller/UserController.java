package com.kakao.cafe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users/login")
    public String login() {
        logger.info("index");

        return "/user/form";
    }

    @GetMapping("/users")
    public String getUsers() {
        // show all users
        return "/user/list";
    }

    @PostMapping("/users")
    public String signup() {
        // signup and redirect
        return "redirect:/users";
    }
}
