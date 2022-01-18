package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.LoginRequest;
import com.kakao.cafe.user.dto.SignupRequest;
import com.kakao.cafe.user.service.UserService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final String SESSION_USER = "sessionUser";
    private final UserService userService;

    @PostMapping
    public String signup(@Valid SignupRequest request) {
        User user = request.toEntity();
        userService.signup(user);
        return "redirect:/user";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserById(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.getProfileById(userId));
        return "user/profile";
    }

    @PostMapping("/login")
    public String login(LoginRequest request, HttpSession session) {
        session.setAttribute(SESSION_USER, userService.login(request));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
