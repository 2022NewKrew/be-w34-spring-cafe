package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String userList(Model model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "/user/list";
    }

    @PostMapping("/users")
    public String userPost(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String userInfo(@PathVariable String userId, Model model) {
        User user = userService.findById(userId);
        System.out.println(user);
        model.addAttribute("user", user);
        return "user/profile";
    }

}
