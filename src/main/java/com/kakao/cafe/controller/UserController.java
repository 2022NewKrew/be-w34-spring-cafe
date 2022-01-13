package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "/user/list";
    }

    @PostMapping("/user/create")
    public String signUp(User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("userId", userId);
        return "/user/updateForm";
    }

    @PostMapping("/user/edit")
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

}
