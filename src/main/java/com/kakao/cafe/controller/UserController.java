package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String signup(User user){
        userService.join(user);
        return "redirect:/users";
    }

    @GetMapping
    public String viewUserList(Model model){
        log.info("viewUserList");
        model.addAttribute("users", userService.findUsers());
        return "/users/list";
    }

    @GetMapping("/{userId}")
    public String viewProfile(@PathVariable Long userId, Model model){
        model.addAttribute("user", userService.findOne(userId));
        return "/users/profile";
    }

    @GetMapping("/{userId}/updateForm")
    public String updateForm(@PathVariable Long userId, Model model){
        User user = userService.findOne(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());

        return "/users/updateForm";
    }

    @PostMapping("/{userId}/updateForm")
    public String updateProfile(@PathVariable Long userId, User user){
        log.info("update Profile");
        userService.updateUser(userId, user);
        return "redirect:/users";
    }
}
