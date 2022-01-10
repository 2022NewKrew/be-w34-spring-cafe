package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public String findUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "list";
    }

    @GetMapping("users/{userId}")
    public String findUserOne(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.findByUserId(userId));
        return "profile";
    }

    @GetMapping("signup")
    public String signup() {
        return "form";
    }

    @PostMapping("users")
    public String createUser(@ModelAttribute UserForm userForm) {
        User user = new User(userForm.getUserId(), userForm.getPassword(), userForm.getName(), userForm.getEmail());
        String userId = userService.create(user);
        logger.info(userId + " success");
        return "redirect:/users";
    }

}
