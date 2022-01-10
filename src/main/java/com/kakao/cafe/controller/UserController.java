package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
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
    public String createUser(@ModelAttribute User user){
        if(userService.checkDuplicateUserId(user.getUserId())){
            throw new IllegalArgumentException("이미 등록된 사용자 입니다.");
        }

        userService.join(user);
        return "redirect:/user";
    }

    @GetMapping("/user/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model){
        Optional<User> profile = userService.findProfile(userId);

        if(profile.isEmpty()){
            throw new IllegalArgumentException("등록되지 않은 사용자 입니다.");
        }

        model.addAttribute("user", profile.get());
        return "user/profile";
    }
}
