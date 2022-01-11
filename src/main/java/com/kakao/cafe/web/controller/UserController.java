package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        logger.info("GET /user/form: response user form page");
        return "user/form";
    }

    @PostMapping("/users")
    public String createUser(User user) {
        logger.info("POST /users: request {}", user);
        // user 생성
        user.setId(++sequence);
        users.add(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        logger.info("GET /users: response user list page with {}", users);
        // users 조회
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String showUser(Model model, @PathVariable String userId) {
        User user = users.stream().filter(obj -> userId.equals(obj.getUserId())).findAny().orElse(null);
        logger.info("GET /users/{}: response user profile page with {}", userId, user);
        // user 조회
        model.addAttribute("user", user);
        return "user/profile";
    }

}
