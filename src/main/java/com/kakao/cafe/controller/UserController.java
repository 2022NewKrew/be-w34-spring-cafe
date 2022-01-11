package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.logging.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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
