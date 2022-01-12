package com.kakao.cafe.web.controller;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;


@Controller
public class UserController {
    private final UserService userService;
    private final Logger logger;

    UserController() {
        this.userService = new UserService();
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/users/form")
    public String getCreateUserForm() {
        return "/user/form";
    }

    @PostMapping("/users/create")
    public String createUser(String userId, String password, String name, String email) {
        UserDTO userDTO = new UserDTO(userId, password, name, email);
        userService.signUp(userDTO);
        return "redirect:/user";
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.getUserList());
        return "/user/list";
    }

    @GetMapping("/users/login")
    public String getLoginForm() { return "/user/login"; }

    @GetMapping("/users/{userId}")
    public String getUserProfile(Model model, @PathVariable String userId) {
        User user = userService.getUserById(userId);
        System.out.println(user);
        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());
        return "/user/profile";
    }
}
