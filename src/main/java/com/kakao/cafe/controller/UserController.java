package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    UserService userService;

    @Autowired
    public void setUserService(final UserService userService) {
        this.userService = Objects.requireNonNull(userService);
    }

    @GetMapping("/user/form")
    public String getSignUp() {
        return "user/form";
    }

    @PostMapping("/user")
    public String processSignUp(@NonNull User user) {
        userService.add(user);
        logger.info("New User added: " + user);
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String getUserList(Model model) {
        model.addAttribute("userlist", userService.getList());
        return "userlist";
    }
}
