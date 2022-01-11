package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.getUserList());
        return "users/list";
    }

    @PostMapping("/users")
    public String register(UserDto requestDto) {
        userService.register(requestDto);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String getUserByUserId(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user",
                userService.findByUserId(userId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 아이디의 유저가 존재하지 않습니다.")));
        return "users/profile";
    }
}
