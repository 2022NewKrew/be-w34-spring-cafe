package com.kakao.cafe.controller;

import com.kakao.cafe.dto.LoginRequest;
import com.kakao.cafe.dto.UserRegisterRequest;
import com.kakao.cafe.service.UserService;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.getUserList());
        return "users/list";
    }

    @PostMapping
    public String register(@Valid UserRegisterRequest requestDto) {
        userService.register(requestDto);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("user", userService.getUserProfileById(id));
        return "users/profile";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "users/login";
    }

    @PostMapping("/login")
    public String login(LoginRequest requestDto, HttpSession session) {
        session.setAttribute("sessionUser", userService.login(requestDto));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
