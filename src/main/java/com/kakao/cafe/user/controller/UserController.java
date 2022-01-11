package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.entity.User;
import com.kakao.cafe.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserService userService = new UserService();

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findUsers());
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String getProfile(Model model, @PathVariable String userId) {
        User user = userService.findOneByUserId(userId);
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("userInfo", user);
        return "user/profile";
    }

    //----------------------

    @PostMapping("/users")
    public String createUser(HttpSession session, String userId, String password, String name, String email) {
        User user = new User(0, userId, password, name, email);
        userService.addUser(user);

        session.setAttribute("user", user);

        return "redirect:/users";
    }
}
