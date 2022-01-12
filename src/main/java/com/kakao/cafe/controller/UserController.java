package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.dto.user.UserCreateRequest;
import com.kakao.cafe.dto.user.UserUpdateRequest;
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
    public String signUp(UserCreateRequest userCreateRequest) {
        userService.signUp(userCreateRequest);

        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String profile(@PathVariable Long userId, Model model) {
        UserDto user = userService.findById(userId);
        model.addAttribute("user", user);

        return "user/profile";
    }

    @GetMapping("/{userId}/form")
    public String updateForm(@PathVariable Long userId, Model model) {
        UserDto user = userService.findById(userId);
        model.addAttribute("user", user);

        return "user/updateForm";
    }

    @PutMapping("/{userId}/update")
    public String updateUser(@PathVariable Long userId, UserUpdateRequest userUpdateRequest) {
        userService.update(userId, userUpdateRequest);

        return "redirect:/";
    }
}
