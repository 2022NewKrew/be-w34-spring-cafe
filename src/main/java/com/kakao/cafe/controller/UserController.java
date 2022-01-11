package com.kakao.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserList;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserList userList = new UserList();

    public UserController() {}

    @PostMapping("/create")
    public String createUser(User user) {
        userList.addUser(user);
        return "redirect:/users";
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userList.getUserList());
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        model.addAttribute("user", userList.getUser(userId));
        return "user/profile";
    }
}

