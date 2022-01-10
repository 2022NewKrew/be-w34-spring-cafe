package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<User> users = new ArrayList<>();
    private UserService userService = new UserService();

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", users);
        return "/user/list";
    }

    @PostMapping("/user/create")
    public String signUp(String userId, String password, String name, String email) {
        int index = users.size() + 1;
        User user = new User(index,userId, password, name, email);
        users.add(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        User user = userService.getUser(userId, users);
        model.addAttribute("user", user);
        return "/user/profile";
    }
}
