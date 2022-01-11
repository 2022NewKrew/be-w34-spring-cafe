package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    UserService userService = new UserService();

    @PostMapping
    public String signup(User user){
        logger.info(user.toString());
        userService.join(user);
        return "redirect:/users";
    }

    @GetMapping
    public String viewUserList(Model model){
        logger.info("viewUserList");
        logger.info(String.valueOf(userService.findUsers().size()));
        model.addAttribute("users", userService.findUsers());
        return "/users/list";
    }

    @GetMapping("/{userId}")
    public String viewProfile(@PathVariable Long userId, Model model){
        userService.findOne(userId)
                .ifPresent(user -> {
                    model.addAttribute("user", user);
                });

        return "/users/profile";
    }
}
