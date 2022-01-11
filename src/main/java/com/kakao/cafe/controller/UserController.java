package com.kakao.cafe.controller;

import com.kakao.cafe.CafeApplication;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(CafeApplication.class);

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/form")
    public String getSignUp() {
        return "user/form";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute(model.addAttribute("users", users));
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable UUID userId, Model model) {
        userService.findById(userId).ifPresent(user -> model.addAttribute("user", user));
        return "user/profile";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute CreateUserDto createUserDto) {
        UUID createdUserId = userService.join(createUserDto);
        logger.info("[Log] 유저가 생성되었습니다. {}", createdUserId);
        return "redirect:/users";
    }
}
