package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    // 회원 목록 페이지
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", this.userService.getUserList());
        return "user/list";
    }

    // 회원 정보 페이지
    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable String userId, Model model) {
        model.addAttribute("user", this.userService.getUserByUserId(userId));
        return "user/profile";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String signUp() {
        return "user/form";
    }

    // 회원 가입 로직
    @PostMapping("/user/signup")
    public String addNewUser(UserDto userDto) {
        log.info("{}", userDto.getUserId());
        log.info("{}", userDto.getPassword());
        log.info("{}", userDto.getName());
        log.info("{}", userDto.getEmail());
        this.userService.saveNewUser(userDto);
        return "redirect:/users";
    }

}
