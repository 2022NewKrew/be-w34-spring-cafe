package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userListView(Model model) {
        List<User> users = userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String profileView(@PathVariable String userId, Model model) {
        User user = userService.filterUserById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/signup")
    public String signupView() {
        return "user/form";
    }

    @PostMapping("/signup")
    public String signup(User user) {
        userService.signupUser(user);
        return "redirect:";
    }
}
