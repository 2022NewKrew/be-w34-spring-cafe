package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private List<User> users = new ArrayList<>();

    @GetMapping("")
    public String users(Model model) {
        model.addAttribute("users", users);
        return "/user/list";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "/user/form";
    }

    @PostMapping("/signup")
    public String createUser(User user) {
        users.add(user);
        return "redirect:/users";
    }
}
