package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/user/list";
    }

    @GetMapping("{userId}")
    public String findUserOne(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.findByUserId(userId));
        return "/user/profile";
    }

    @PostMapping
    public String signup(@ModelAttribute UserDto userDto) {
        int id = userService.create(userDto);
        return "redirect:/users";
    }

    @GetMapping("{userId}/form")
    public String showUpdateForm(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.findByUserId(userId));
        return "/user/userform";
    }

    @PostMapping("{userId}")
    public String updateUser(@PathVariable String userId, @ModelAttribute UserDto userDto) {
        userService.updateUser(userId, userDto);
        return "redirect:/users";
    }



}
