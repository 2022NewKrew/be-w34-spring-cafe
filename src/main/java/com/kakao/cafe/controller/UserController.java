package com.kakao.cafe.controller;

import com.kakao.cafe.DTO.SignInDTO;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public String SignUp(@ModelAttribute SignInDTO userInfo) {
        logger.info("Attempt to SignUp; ID={}", userInfo.getUserId());

        if (UserService.SignUp(userInfo)) {
            logger.info("Successful SignUp; ID={}", userInfo.getUserId());
            return "redirect:/user/list";
        }

        logger.info("SignUp Failed. already exist ID={}", userInfo.getUserId());
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String getUserList(Model model) {
        model.addAttribute("users", UserService.getUserInfoLst());
        return "user/list";
    }

    @GetMapping("/prof/{userId}")
    public String getUserProfile(Model model, @PathVariable String userId) {
        logger.info("User profile request; ID={}", userId);
        model.addAttribute("userProfile", UserService.getUserProfile(userId));
        return "user/profile";
    }
}
