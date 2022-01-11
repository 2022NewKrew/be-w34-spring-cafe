package com.kakao.cafe.web;

import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.web.dto.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/form")
    public String form() {
        return "/user/form";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/user/list";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute UserCreateRequest requestDto) {
        userService.create(requestDto.toEntity());
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        return "/user/profile";
    }
}
