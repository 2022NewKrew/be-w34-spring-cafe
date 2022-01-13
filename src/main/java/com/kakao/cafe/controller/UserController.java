package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.SignUpDTO;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @PostMapping("/create")
    public String signUp(@ModelAttribute @Validated SignUpDTO userDTO){
        userService.signUp(userDTO);
        return "redirect:/users";
    }

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserByUserId(@PathVariable("userId") String userId, Model model){
        model.addAttribute("user", userService.getUser(userId));
        return "user/profile";
    }

}