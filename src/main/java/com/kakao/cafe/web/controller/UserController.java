package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final List<User> users = new ArrayList<>();
    private static int sequence = 0;

    @GetMapping("/user/form")
    public String form() {
        return "user/form";
    }

    @PostMapping("/users")
    public String createUser(User user) {
        logger.info("POST /users: {}", user);
        // user 생성
        user.setId(++sequence);
        users.add(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        logger.info("GET /users: {}", users);
        // users 조회
        model.addAttribute("users", users);
        return "user/list";
    }

}
