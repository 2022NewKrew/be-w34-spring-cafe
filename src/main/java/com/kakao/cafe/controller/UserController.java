package com.kakao.cafe.controller;

import com.kakao.cafe.DTO.SignUpDTO;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public String SignUp(@ModelAttribute SignUpDTO userInfo) {
        logger.info("Attempt to SignUp; ID={}", userInfo.getUserId());

        if (userService.signUp(userInfo)) {
            logger.info("Successful SignUp; ID={}", userInfo.getUserId());
            return "redirect:/user/list";
        }

        logger.info("SignUp Failed. already exist ID={}", userInfo.getUserId());
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getUserInfoLst());
        return "user/list";
    }

    @GetMapping("/prof/{userId}")
    public String getUserProfile(Model model, @PathVariable String userId) {
        logger.info("User profile request; ID={}", userId);
        model.addAttribute("userProfile", userService.getUserProfile(userId));
        return "user/profile";
    }
}
