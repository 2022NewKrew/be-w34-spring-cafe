package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserMapper userMapper;

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("users", userMapper.findAll());
        return "/user/list";
    }

    @GetMapping("/signup")
    public String userSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "/user/form";
    }

    @PostMapping("/signup")
    public String userSignUp(User user) {
        userMapper.insert(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}")
    public String userProfile(@PathVariable Long id, Model model) {
        model.addAttribute("user", userMapper.findById(id));
        return "/user/profile";
    }
}
