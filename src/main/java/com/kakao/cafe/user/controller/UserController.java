package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.User;
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
    public String signup(@Valid UserSignupRequest request, Model model) {
        User user = request.toEntity();
        userService.signup(user);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getUsername());
        return "/user/signup_success";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserByUsername(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.getProfileById(userId));
        return "user/profile";
    }
}
