package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.request.SignupReqDto;
import com.kakao.cafe.user.dto.response.UserResDto;
import com.kakao.cafe.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserResDto> users = userService.getUsers();
        model.addAttribute("users", users);
        return "/user/list";
    }

    @PostMapping("/users/signup")
    public String signup(@ModelAttribute SignupReqDto signupReqDto) {
        // signup and redirect
        userService.signup(signupReqDto);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        UserResDto user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "/user/profile";
    }
}
