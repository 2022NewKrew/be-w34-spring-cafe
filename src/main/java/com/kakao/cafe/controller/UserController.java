package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService = new UserService();

    @PostMapping("")
    public String signUp(@ModelAttribute User user){
        logger.info("POST:/users 회원가입 {}", user.getUserId());
        userService.signUp(user);
        return "redirect:/users";
    }

    @GetMapping("")
    public String viewUsersList(Model model){
        logger.info("GET:/users 유저목록조회");
        model.addAttribute("users", userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String viewPersonalUser(@PathVariable String userId, Model model){
        logger.info("GET:/users/{} 유저정보조회", userId);
        model.addAttribute("user", userService.findOneUser(userId));
        return "user/profile";
    }


}
