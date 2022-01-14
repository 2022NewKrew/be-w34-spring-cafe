package com.kakao.cafe.controller;

import com.kakao.cafe.DTO.SignInDTO;
import com.kakao.cafe.domain.UserDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final UserDB userDB = new UserDB();
    Logger logger = LoggerFactory.getLogger(UserController.class);

    String ALREADY_EXISTS_ID_MESSAGE = "This ID is already exists";

    @PostMapping("/create")
    public String SignUp(@ModelAttribute SignInDTO userInfo) {
        logger.debug("Attempt to SignUp; ID={}", userInfo.getUserId());

        if (userDB.SignUp(userInfo.getUserId(), userInfo.getPassword(), userInfo.getName(), userInfo.getEmail())) {
            logger.debug("Successful SignUp; ID={}", userInfo.getUserId());
            return "redirect:/user/list";
        }

        logger.debug("SignUp Failed. already exist ID={}", userInfo.getUserId());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ALREADY_EXISTS_ID_MESSAGE);
    }

    @GetMapping("/list")
    public String getUserList(Model model) {
        model.addAttribute("users", userDB.getUserInfoLst());
        return "user/list";
    }

    @GetMapping("/prof/{userId}")
    public String getUserProfile(Model model, @PathVariable String userId) {
        logger.debug("User profile request; ID={}", userId);
        model.addAttribute("userProfile", userDB.getUserProfile(userId));
        return "user/profile";
    }
}
