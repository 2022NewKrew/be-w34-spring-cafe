package com.kakao.cafe.controller;

import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.domain.UserRequest;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String users(Model model) {
        List<UserDto> users = userService.getUsers();
        model.addAttribute("users", users);

        return "user/list";
    }

    @PostMapping
    public String signUp(UserRequest userRequest) {
        userService.signUp(userRequest);

        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String profile(@PathVariable Long userId, Model model) {
        UserDto user = userService.findById(userId);
        model.addAttribute("user", user);

        return "user/profile";
    }
}
