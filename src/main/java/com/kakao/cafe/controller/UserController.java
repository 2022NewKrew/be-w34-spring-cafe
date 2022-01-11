package com.kakao.cafe.controller;

import com.kakao.cafe.CafeApplication;
import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.service.UserService;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(CafeApplication.class);

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/form")
    public String getSignUp(Model model) {
        return "user/form";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute CreateUserDto createUserDto) {
        logger.info("[Logging] User created {}, {}, {}", createUserDto.getEmail(),
            createUserDto.getNickname(), createUserDto.getPassword());
        UUID uuid = userService.join(createUserDto);
        logger.info("[Logging] User created {}", uuid);
        return "redirect:/users";
    }
}
