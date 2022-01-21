package com.kakao.cafe.user.controller;

import com.kakao.cafe.exception.InvalidFormatException;
import com.kakao.cafe.user.dto.UserCreationForm;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUserView());
        return "user/list";
    }

    @PostMapping("")
    public String processCreationForm(@Validated UserCreationForm userCreationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        userService.addUser(userCreationForm);
        return "redirect:/users";
    }

    @GetMapping("/{username}")
    public String showUser(@PathVariable String username, Model model) {
        model.addAttribute("user", userService.getUserViewByUsername(username));
        return "user/profile";
    }
}
