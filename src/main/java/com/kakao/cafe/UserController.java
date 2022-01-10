package com.kakao.cafe;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("user/signup")
    public String getSignup() {
        return "user/form";
    }

    @PostMapping("users")
    public String postSignup(String userId, String password, String name, String email) {
        User user = new User(userId, password, name, email);
        logger.info("POST request on Signup -> {}", user);
        return "redirect:/users";
    }
}
