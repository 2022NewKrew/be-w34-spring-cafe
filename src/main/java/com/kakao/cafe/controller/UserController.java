package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        List<User> userList = userService.getUsers();
        model.addAttribute("users", userList);
        return "user/list";
    }

    @PostMapping
    public String createUser(User user) {
        userService.join(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String user(@PathVariable String userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }
}