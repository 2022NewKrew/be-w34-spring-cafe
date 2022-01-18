package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.entity.User;
import com.kakao.cafe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findUsers());
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String getProfile(Model model, @PathVariable String userId) {
        Optional<User> user = userService.findOneByUserId(userId);
        if (user.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("userInfo", user.get());
        return "user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String updateProfileForm(Model model, @PathVariable String userId) {
        model.addAttribute("userId", userId);
        return "user/updateForm";
    }

    @PostMapping("/users")
    public String createUser(HttpSession session, @Valid @ModelAttribute("users") User user) {
        userService.addUser(user);
        session.setAttribute("sessionedUser", user);
        return "redirect:/users";
    }

    @PostMapping("/users/login")
    public String loginUser(HttpSession session, String userId, String password) {
        User user = userService.loginUser(userId, password);
        if (user == null) {
            throw new IllegalStateException("ID 혹은 Password가 잘못되었습니다.");
        }
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    @PatchMapping("/users")
    public String updateUserProfile(@Valid @ModelAttribute("users") User user) {
        userService.update(user);
        return "redirect:/users";
    }


}
