package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserRequestDto;
import com.kakao.cafe.dto.user.UserResponseDto;
import com.kakao.cafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUserList(Model model){
        model.addAttribute("users", userService.findAllUser());
        return "user/list";
    }

    @GetMapping("/user/form")
    public String userForm(){
        return "user/form";
    }

    @PostMapping("/user")
    public String createUser(@ModelAttribute UserRequestDto userRequestDto){
        User user = userService.join(userRequestDto);
        log.info("Create User - {}", user);
        return "redirect:";
    }

    @GetMapping("/user/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model){
        UserResponseDto profile = userService.findProfile(userId);
        model.addAttribute("user", profile);
        return "user/profile";
    }
}
