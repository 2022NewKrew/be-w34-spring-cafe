package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user")
    public String signUp(User user) {
        UserList.addUserList(user);
        logger.info("user create : {}", user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/form")
    public String openSignUp() {
        return "/user/form";
    }
}
