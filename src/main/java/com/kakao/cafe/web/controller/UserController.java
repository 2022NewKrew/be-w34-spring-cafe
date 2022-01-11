package com.kakao.cafe.web.controller;

import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value="/user/form.html")
    public String getSignUp(String id, String password, String name, String email) {
        User user = new User(id, password, name, email);
        return "user/form";
    }

    @PostMapping(value = "/user/create")
    public String postSignUp(User user) {
        logger.info("user:{}",user);
        return "redirect:/user/login.html";
    }
}
