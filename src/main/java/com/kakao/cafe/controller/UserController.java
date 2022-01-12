package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserRegisterRequest;
import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.getUserList());
        return "users/list";
    }

    @PostMapping("/users")
    public String register(UserRegisterRequest requestDto) {
        userService.register(requestDto);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String getUserByUserId(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user",
                userService.findByUserId(userId)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND)));
        return "users/profile";
    }
}
