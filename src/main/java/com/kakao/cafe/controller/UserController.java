package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "/user/list";
    }

    @PostMapping
    public String signUp(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String userInfo(@PathVariable String userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

}
