package com.kakao.cafe.web.controller;

import domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping(value="/user/form.html")
    public String getSignUp(String id, String password, String name, String email) {
        User user = new User(id, password, name, email);
        return "user/form";
    }

    @PostMapping(value = "/user/create")
    public String postSignUp(String id, String password, String name, String email) {
        User user = new User(id, password, name, email);
        return "redirect:/login";
    }
}
