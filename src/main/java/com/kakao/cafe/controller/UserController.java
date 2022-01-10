package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/user/form")
    public  String createForm(UserForm form)
    {
        return "user/form";
    }
    @PostMapping("/users")
    public  String create(UserForm form)
    {

        return "redirect:/users";
    }
}
