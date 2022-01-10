package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {
    private final List<User> users = new ArrayList<>();

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", users);
        model.addAttribute("users.index", 1);
        return "list";
    }

    @PostMapping()
    public String register(User user) {
        users.add(user);
        return "redirect:users";
    }

    @GetMapping("/{userId}")
    public String showUserProfile(@PathVariable String userId, Model model) {
        User user = findUser(userId);
        model.addAttribute("user", user);
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
