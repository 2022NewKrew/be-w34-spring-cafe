package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute User user) {
        userService.join(user);
        return "redirect:/users";
    }

    @GetMapping("/user/form")
    public String userForm() {
        return "user/form";
    }

    @GetMapping("/user/login")
    public String userLogin() {
        return "user/login";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        List<User> userList = userService.findEvery();
        model.addAttribute("users", userList);
        return "user/list";
    }

    @GetMapping("/users/{id}")
    public String userProfile(@PathVariable("id") String userId, Model model) {
        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

}
