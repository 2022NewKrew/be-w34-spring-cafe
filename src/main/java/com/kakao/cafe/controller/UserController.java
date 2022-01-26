package com.kakao.cafe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(UserDto userDTO) {
        userService.save(userDTO);

        return "redirect:/users";
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll().getUsers());

        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.findById(userId));

        return "user/profile";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        if (!userService.login(userId, password)) {
            return "user/login_failed";
        }

        session.setAttribute("userId", userId);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

