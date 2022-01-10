package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/signup")
    public String createUser() {
        return "user/form";
    }

    @PostMapping("/signup")
    public String createUser(User user) {
        users.add(user);
        logger.info(user.getName());
        logger.info(user.getUserId());
        logger.info(user.getEmail());

        return "user/list";
    }
}
