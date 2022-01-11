package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserCreateRequest;
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
    public String signUp(@ModelAttribute UserCreateRequest user){
        logger.info("POST:/users 회원가입 {}", user.getNickname());
        userService.signUp(user);
        return "redirect:/users";
    }

    @GetMapping("")
    public String viewUsersList(Model model){
        logger.info("GET:/users 유저목록조회");
        model.addAttribute("users", userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/{id}")
    public String viewPersonalUser(@PathVariable Long id, Model model){
        logger.info("GET:/users/{} 유저정보조회", id);
        model.addAttribute("user", userService.findOneUser(id));
        return "user/profile";
    }


}
