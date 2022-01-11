package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping()
    public String create(UserDto userDto) {
        logger.info("create: {}, {}", userDto.getUserId(), userDto.getPassword());
        userService.create(userDto);
        return "redirect:";
    }

    @GetMapping()
    public String getUserList(Model model) {
        List<UserDto> users = userService.readAll();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable String userId, Model model) {
        UserDto user = userService.read(userId).get();
        model.addAttribute("user", user);
        return "user/profile";
    }
}
