package com.kakao.cafe.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/user/form")
    public String userForm() {
        return "user/form";
    }

    @GetMapping("/user/list")
    public String userList() {
        return "user/list";
    }

    @PostMapping("/user/create")
    public String userCreate(){
        return "redirect:/user/list";
    }
}
