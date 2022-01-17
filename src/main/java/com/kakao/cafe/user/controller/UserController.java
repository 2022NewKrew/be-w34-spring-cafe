package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.UserCreationForm;
import com.kakao.cafe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public String processCreationForm(@Validated UserCreationForm userCreationForm) {
        userService.registerUser(userCreationForm);
        return "redirect:/users";
    }

    @GetMapping("")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUserView());
        return "user/list";
    }

    @GetMapping("/{username}")
    public String showUser(@PathVariable String username, Model model) {
        model.addAttribute("user", userService.getUserViewByUsername(username));
        return "user/profile";
    }
}
