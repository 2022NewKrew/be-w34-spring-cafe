package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "/user/list";
    }

    @GetMapping("users/{userId}")
    public String findUserOne(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.findByUserId(userId));
        return "/user/profile";
    }

    @PostMapping("users")
    public String signup(@ModelAttribute UserDto userDto) {
        String userId = userService.create(userDto);
        logger.info(userId + " success");
        return "redirect:/users";
    }

}
