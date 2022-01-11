package com.kakao.cafe.web.controller;

import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersApiController {
    private final UserService userService = new UserService();

    @GetMapping
    String getUsers(Model model) {
        List<Users> userList = userService.getUserList();
        model.addAttribute("users", userList);
        return "users/list";
    }

    @GetMapping("/form")
    String getForm() {
        return "users/form";
    }

    @GetMapping("/login")
    String login() {
        return "users/login";
    }

    @GetMapping("/{id}")
    String findById(@PathVariable Long id, Model model) {
        Users user = userService.getByUserId(id);
        model.addAttribute("user", user);
        return "users/profile";
    }

    @GetMapping("/profile")
    String getProfile(Users user, Model model) {
        model.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping
    String createUser(Users user) {
        userService.addUser(user);
        return "redirect:/users";
    }
}
