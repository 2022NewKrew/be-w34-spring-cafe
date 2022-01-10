package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", users);
        return "list";
    }

    @PostMapping("/users")
    public String register(User user) {
        users.add(user);
        return "redirect:users";
    }

    @GetMapping("/users/{userId}")
    public String showUserProfile(@PathVariable String userId, Model model) {
        User targetUser = findUser(userId);
        model.addAttribute("user", targetUser);
        return "profile";
    }

    private User findUser(String targetId) {
        return users
                .stream()
                .filter(user -> user.getUserId().equals(targetId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾고자 하는 사용자가 없습니다!"));
    }
}
