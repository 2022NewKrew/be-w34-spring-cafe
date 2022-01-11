package com.kakao.cafe.controller;

import com.kakao.cafe.vo.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/form")
    public String createUser() {
        return "user/form";
    }

    @PostMapping("/users")
    public String createUser(User user) {
        users.add(user);
        logger.info("{} {} {} {}", user, user.getUserId(), user.getEmail(), user.getPassword());
        logger.info("{}", users);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String memberList(Model model) {
//        model.addAttribute("users", users);
        for (User user : users) {
            model.addAttribute("users", user);
        }
        return "user/list";
    }
}