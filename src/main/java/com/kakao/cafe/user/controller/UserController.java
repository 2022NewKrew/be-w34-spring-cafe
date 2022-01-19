package com.kakao.cafe.user.controller;

import com.kakao.cafe.exception.InvalidFormatException;
import com.kakao.cafe.user.dto.UserCreationForm;
import com.kakao.cafe.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUserView());
        return "user/list";
    }

    @GetMapping("/users/{username}")
    public String showUser(@PathVariable String username, Model model) {
        model.addAttribute("user", userService.getUserViewByUsername(username));
        return "user/profile";
    }

    @PostMapping("/users")
    public String processCreationForm(@Validated UserCreationForm userCreationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        userService.addUser(userCreationForm);
        return "redirect:/users";
    }
}
