package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/signup")
    public String user() {
        String msg = "hello";
        int v = 2022;
        logger.info("{} Spring api: {}", msg, v);
        return "users/form";
    }
}
