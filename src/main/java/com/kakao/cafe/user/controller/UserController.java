package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();

    @RequestMapping("/abcdef")
    public String user() {
        return "form";
    }

//    @PostMapping("/")

}
