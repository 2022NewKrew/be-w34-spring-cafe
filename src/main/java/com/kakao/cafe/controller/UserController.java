package com.kakao.cafe.controller;

import com.kakao.cafe.DTO.SignInDTO;
import com.kakao.cafe.domain.UserDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final UserDB userDB = new UserDB();
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public String SignUp(@ModelAttribute SignInDTO userInfo) {
        logger.info("Attempt to SignUp; ID={}", userInfo.getUserId());

        if (userDB.SignUp(userInfo.getUserId(), userInfo.getPassword(), userInfo.getName(), userInfo.getEmail())) {
            logger.info("Successful SignUp; ID={}", userInfo.getUserId());
            return "redirect:/user/list";
        }

        logger.info("SignUp Failed. already exist ID={}", userInfo.getUserId());
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String getUserList(Model model) {
        model.addAttribute("users", userDB.getUserInfoLst());
        return "user/list";
    }

    @GetMapping("/prof/{userId}")
    public String getUserProfile(Model model, @PathVariable String userId) {
        logger.info("User profile request; ID={}", userId);
        model.addAttribute("userProfile", userDB.getUserProfile(userId));
        return "user/profile";
    }
}
