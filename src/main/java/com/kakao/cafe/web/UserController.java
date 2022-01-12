package com.kakao.cafe.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/user/form")
    public String userForm() {
        return "/user/form";
    }

    @PostMapping("/users")
    public String userCreate() {
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String userList(){
        return "/user/list";
    }
}
