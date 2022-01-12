package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    UserService userService = new UserService();

    @PostMapping
    public String signup(User user){
        userService.join(user);
        return "redirect:/users";
    }

    @GetMapping
    public String viewUserList(Model model){
        logger.info("viewUserList");
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
        logger.info("update Profile");
        userService.updateUser(userId, user);
        return "redirect:/users";
    }
}
