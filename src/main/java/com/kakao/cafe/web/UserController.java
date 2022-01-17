package com.kakao.cafe.web;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.dto.user.UserCreateCommand;
import com.kakao.cafe.dto.user.UserInfo;
import com.kakao.cafe.dto.user.UserModifyCommand;
import com.kakao.cafe.dto.user.UserProfileInfo;

import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @PostMapping("/users")
    public String addUser(UserCreateCommand ucc) {
        userService.createUser(ucc);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String printProfile(@PathVariable String userId, Model model) {
        UserProfileInfo userInfo = userService.getUserProfile(userId);
        model.addAttribute("user", userInfo);
        return "user/profile";
    }

    @GetMapping("/users/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("sessionedUser");
        if (user == null || !user.getUserId().equals(userId)) {
            return "/error";
        }

        UserInfo userInfo = userService.getUserInfo(userId);
        model.addAttribute("user", userInfo);
        return "user/updateForm";
    }

    @PutMapping("/users/{userId}/update")
    public String updateUser(@PathVariable String userId, UserModifyCommand umc, HttpSession session) {
        User user = (User)session.getAttribute("sessionedUser");
        if (user == null || !user.getUserId().equals(userId)) {
            return "/error";
        }

        if (!user.getPassword().equals(umc.getPassword())) {
            return "redirect:/users/{userId}/form";
        }
        userService.modifyUser(userId, umc);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userService.getUser(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return "user/login_failed";
        }
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
