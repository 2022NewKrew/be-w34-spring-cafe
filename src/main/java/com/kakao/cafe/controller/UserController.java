package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.UserService;
import com.kakao.cafe.domain.user.dto.UserCreateRequestDto;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import com.kakao.cafe.domain.user.dto.UserUpdateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    @GetMapping("/users/{userId}")
    public String getUser(@PathVariable String userId, Model model) {
        UserResponseDto user = userService.retrieveUser(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/users/{userId}/update")
    public String updateUserForm(@PathVariable String userId, Model model) {
        UserResponseDto user = userService.retrieveUser(userId);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PostMapping("/users")
    public String createUser(UserCreateRequestDto requestDto) {
        userService.createUser(requestDto);
        return "redirect:/users";
    }

    @PutMapping("/users/{userId}")
    public String updateUser(@PathVariable String userId, UserUpdateRequestDto requestDto) {
        userService.updateUser(userId, requestDto);
        return "redirect:/users";
    }
}
