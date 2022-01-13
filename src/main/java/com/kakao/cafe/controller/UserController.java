package com.kakao.cafe.controller;

import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.h2.engine.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userListView(Model model) {
        List<UserDto> users = userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String profileView(@PathVariable String userId, Model model) {
        UserDto user = userService.filterUserById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/signup")
    public String signupView() {
        return "user/form";
    }

    @PostMapping("/signup")
    public String signup(UserDto user) {
        userService.signupUser(user);
        return "redirect:";
    }

    @GetMapping("/{userId}/update")
    public String updateView(@PathVariable String userId, Model model) {
        UserDto user = userService.filterUserById(userId);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PutMapping("/{userId}/update")
    public String update(@PathVariable String userId, String password, String newPassword, String email, String name) {
        UserDto user = userService.filterUserById(userId);
        if (user.getPassword().equals(password)) {
            user.setPassword(newPassword);
            user.setEmail(email);
            user.setName(name);
            userService.updateUser(user);
        }
        return "redirect:";
    }
}
