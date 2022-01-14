package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signUp")
    public String signUpView(){
        return "user/signUp";
    }

    @PostMapping("/signUp")
    public String signUpUser(User user){
        userService.signUp(user);
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String userList(Model model){
        model.addAttribute("users",userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/profile/{userId}")
    public String userProfile(Model model, @PathVariable String userId){
        model.addAttribute("user", userService.findUserById(userId));
        return "user/profile";
    }
}
