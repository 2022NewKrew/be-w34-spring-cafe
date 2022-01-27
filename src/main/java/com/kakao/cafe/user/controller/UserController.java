package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.DTO.SignUpDTO;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public String SignUp(@ModelAttribute SignUpDTO userInfo) {
        logger.debug("Attempt to SignUp; ID={}", userInfo.getUserId());

        String ALREADY_EXISTS_ID_MESSAGE = "This ID is already exists";

        if (userService.signUp(userInfo)) {
            logger.debug("Successful SignUp; ID={}", userInfo.getUserId());
            return "redirect:/user/list";
        }

        logger.debug("SignUp Failed. already exist ID={}", userInfo.getUserId());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ALREADY_EXISTS_ID_MESSAGE);
    }

    @GetMapping("/list")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getUserInfoLst());
        return "user/list";
    }

    @GetMapping("/prof/{userId}")
    public String getUserProfile(Model model, @PathVariable String userId) {
        logger.debug("User profile request; ID={}", userId);
        model.addAttribute("userProfile", userService.getUserProfile(userId));
        return "user/profile";
    }
}
