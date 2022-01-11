package com.kakao.cafe.controller;

import com.kakao.cafe.domain.UserDto;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = Objects.requireNonNull(userService);
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "signup.mustache";
    }

    @PostMapping("/user")
    public String processSignUp(
            @NonNull final UserDto userDto,
            @RequestParam("password") @NonNull final String password
    )
    {
        userService.add(userDto, password);
        logger.info("New User added: " + userDto.getId());
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String getUserList(final Model model) {
        model.addAttribute("userlist", userService.getList());
        return "user.mustache";
    }

    @GetMapping("/user/{id}")
    public String getUserProfile(
            @PathVariable("id") @NonNull final String id,
            final Model model
    )
    {
        try {
            final UserDto userDto = userService.getUser(id);
            model.addAttribute("user", userDto);
        } catch (NoSuchElementException ignored) {}

        return "profile.mustache";
    }
}
