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
    private final List<User> users = new ArrayList<>();

    @GetMapping("user/signup")
    public String getSignup() {
        return "user/form";
    }

    @PostMapping("users")
    public String postSignup(String userId, String password, String name, String email) {
        int id = users.size() + 1;
        User user = new User(id, userId, password, name, email);
        users.add(user);
        logger.info("POST request on Signup -> {}", user);
        return "redirect:users";
    }

    @GetMapping("users")
    public String getUserList(Model model) {
        model.addAttribute("users", users);
        return "user/list";
    }
}
