package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.UserService;
import com.kakao.cafe.domain.user.dto.UserCreateRequestDto;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        List<UserResponseDto> users = userService.retrieveAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        UserResponseDto user = userService.retrieveUser(id);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/users/create")
    public String form(UserCreateRequestDto requestDto) {
        userService.createUser(requestDto);
        return "redirect:/users";
    }
}
