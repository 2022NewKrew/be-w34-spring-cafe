package com.kakao.cafe.controller;

import com.kakao.cafe.DTO.SignInDTO;
import com.kakao.cafe.domain.UserDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final UserDB userDB = new UserDB();
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public String SignUp(@RequestParam SignInDTO userInfo) {
        logger.info("Attempt to SignUp; ID={}", userInfo.getUID());

        if (userDB.SignUp(userInfo.getUID(), userInfo.getPassword(), userInfo.getName(), userInfo.getEmail())) {
            logger.info("Successful SignUp; ID={}", userInfo.getUID());
            return "redirect:/user/list";
        }

        logger.info("SignUp Failed. already exist ID={}", userInfo.getUID());
        return "redirect:/user/list";
    }

    @GetMapping()
    public String getUserList(Model model) {
        model.addAttribute("users", userDB.getUserInfoLst());
        return "user/list";
    }
}
