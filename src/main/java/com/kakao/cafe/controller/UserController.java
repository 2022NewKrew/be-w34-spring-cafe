package com.kakao.cafe.controller;

import com.kakao.cafe.domain.dtos.UserSaveDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "user/form";
    }

    @GetMapping("/users")
    public String userPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute UserSaveDto dto) {
        userService.save(dto);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String profilePage(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user/profile";
    }
}
