package com.kakao.cafe.web;

import com.kakao.cafe.domain.dto.UserCreateCommand;
import com.kakao.cafe.domain.dto.UserModifyCommand;
import com.kakao.cafe.domain.entity.User;

import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        User target = userService.getUser(userId);
        model.addAttribute("name", target.getName())
                .addAttribute("email", target.getEmail());
        return "user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model) {
        User target = userService.getUser(userId);
        model.addAttribute("userId", target.getUserId())
                .addAttribute("password", target.getPassword())
                .addAttribute("name", target.getName())
                .addAttribute("email", target.getEmail());
        return "user/updateForm";
    }

    @PostMapping("users/{userId}/update")
    public String updateUser(@PathVariable String userId, UserModifyCommand umc) {
        userService.modifyUser(userId, umc);
        return "redirect:/users/";
    }
}
