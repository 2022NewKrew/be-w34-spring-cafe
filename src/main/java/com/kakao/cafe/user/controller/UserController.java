package com.kakao.cafe.user.controller;

import com.kakao.cafe.exception.InvalidFormatException;
import com.kakao.cafe.user.dto.LoggedInUser;
import com.kakao.cafe.user.dto.UserCreationForm;
import com.kakao.cafe.user.dto.UserLoginForm;
import com.kakao.cafe.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String processCreationForm(@Validated UserCreationForm userCreationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        userService.registerUser(userCreationForm);
        return "redirect:/users";
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

    @GetMapping("/login")
    public String getLogin() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Validated UserLoginForm userLoginForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        LoggedInUser loggedInUser = userService.login(userLoginForm);
        session.setAttribute("loggedInUser", loggedInUser);
        return "redirect:/";
    }

    @DeleteMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
        // return "/";
        // return "user/list";
    }
}
