package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.UserSignupRequest;
import com.kakao.cafe.user.service.UserService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String signup(@Valid UserSignupRequest request) {
        userService.signup(request.toEntity());
        return "redirect:user";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/{username}")
    public String getUserByUsername(@PathVariable String username, Model model) {
        model.addAttribute("user", userService.getUserByUsername(username));
        return "user/profile";
    }
}
