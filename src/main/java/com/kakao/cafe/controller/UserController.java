package com.kakao.cafe.controller;

import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String goRegisterView() {
        return "user/register";
    }

    @PostMapping("/register")
    public String userRegister(UserDto userDto) {
        userService.registerUser(userDto);
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/view/{userId}")
    public String userView(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "user/view";
    }

    @GetMapping("/modify/{userId}")
    public String userModifyView(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "user/modify";
    }

    @PutMapping("/modify/{userId}")
    public String userModify(@PathVariable("userId") String userId, String oldPassword,
                             String newPassword, String name, String email) {
        userService.findUserByLoginInfo(userId, oldPassword);
        userService.modifyUser(UserDto.builder()
                .userId(userId)
                .password(newPassword)
                .name(name)
                .email(email).build());
        return "redirect:/user/list";
    }
}
