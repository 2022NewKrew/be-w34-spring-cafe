package com.kakao.cafe.web;

import com.kakao.cafe.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller()
public class UserController {

    private final List<User> users = new ArrayList<>();

    @GetMapping("/users/signup")
    public String form() {
        return "./user/form";
    }

    @PostMapping("/users")
    public String signUp(User user) {
        users.add(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String users(Model model) {
        System.out.println(users);
        model.addAttribute("users", users);
        return "./user/list";
    }

    @GetMapping("/users/{userId}")
    public String profile(@PathVariable String userId, Model model) {
        User matchedUser = users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
        model.addAttribute("matchedUser", matchedUser);
        return "./user/profile";
    }

    @GetMapping("/users/login")
    public String login() {
        return "./user/login";
    }

}
