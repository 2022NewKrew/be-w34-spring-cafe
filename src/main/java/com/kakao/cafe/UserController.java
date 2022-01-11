package com.kakao.cafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final List<User> users = new ArrayList<>();

    @PostMapping("/user/create")
    public String postUserCreate(String userId, String password, String name, String email) {
        logger.info("[postUserCreate] userId = {}, password = {}, name = {}, email = {}", userId, password, name, email);
        users.add(new User(userId, password, name, email));
        return "hello";
    }
}
