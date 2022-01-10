package com.kakao.cafe.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController {
    private final List<User> users = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/users")
    public String save(
            @RequestParam String userId,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String email
    ) {
        logger.info("회원 가입");
        User newUser = new User(userId, password, name, email);
        users.add(newUser);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        logger.info("회원 목록");
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String findById(@PathVariable String userId, Model model) {
        logger.info("회원 프로필");
        User target = users.stream()
                .filter(user -> user.isSameUser(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        model.addAttribute("profile", target);
        return "user/profile";
    }
}
