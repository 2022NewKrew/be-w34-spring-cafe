package com.kakao.cafe.web;

import com.kakao.cafe.dto.user.UserCreateCommand;
import com.kakao.cafe.dto.user.UserInfo;
import com.kakao.cafe.dto.user.UserModifyCommand;
import com.kakao.cafe.dto.user.UserProfileInfo;

import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @PostMapping("/users")
    public String addUser(UserCreateCommand ucc) {
        userService.createUser(ucc);
        return "redirect:/users/";
    }

    @GetMapping("/users/{userId}")
    public String printProfile(@PathVariable String userId, Model model) {
        UserProfileInfo userInfo = userService.getUserProfile(userId);
        model.addAttribute("user", userInfo);
        return "user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model) {
        UserInfo userInfo = userService.getUser(userId);
        model.addAttribute("user", userInfo);
        return "user/updateForm";
    }

    @PutMapping("users/{userId}/update")
    public String updateUser(@PathVariable String userId, UserModifyCommand umc) {
        userService.modifyUser(userId, umc);
        return "redirect:/users/";
    }
}
