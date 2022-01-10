package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", users);
        return "/user/list";
    }

    @GetMapping("/users/{userId}")
    public String profile(@PathVariable String userId, Model model) {
        User selectedUser = users.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
        model.addAttribute("user", selectedUser);
        return "/user/profile";
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
