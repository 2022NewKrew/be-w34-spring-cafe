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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/user/signup")
    public String getSignUp() {
        return "user/signup";
    }

    @PostMapping("/user")
    public String processSignUp(@NonNull final User user) {
        userService.add(user);
        logger.info("New User added: " + user);
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String getUserList(final Model model) {
        model.addAttribute("userlist", userService.getList());
        return "user";
    }

    @GetMapping("/user/{id}")
    public String getUserProfile(
            @PathVariable("id") @NonNull final String id,
            final Model model
    )
    {
        final User user = userService.getUser(id);
        if (user.isNotNone()) {
            model.addAttribute("user", user);
        }
        return "user/profile";
    }
}
