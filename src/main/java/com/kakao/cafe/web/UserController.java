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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String updateForm(@PathVariable String userId, Model model,
                             @SessionAttribute("sessionedUser") User user) {
        if (userNotExists(user) || userNoPermission(user, userId)) {
            return "/noPermission";
        }

        UserInfo userInfo = userService.getUserInfo(userId);
        model.addAttribute("user", userInfo);
        return "user/updateForm";
    }

    @PutMapping("/users/{userId}/update")
    public String updateUser(@PathVariable String userId, UserModifyCommand umc,
                             @SessionAttribute("sessionedUser") User user, RedirectAttributes ra) {
        if (userNotExists(user) || userNoPermission(user, userId)) {
            return "/noPermission";
        }

        if (userPasswordNotMatching(user, umc)) {
            ra.addFlashAttribute("passwordNotMatching", true);
            return "redirect:/users/{userId}/form";
        }
        userService.modifyUser(userId, umc);
        return "redirect:/users";
    }

    @GetMapping("/users/login")
    public String showLoginForm(Model model) {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session, RedirectAttributes ra) {
        User user = userService.getUser(userId);
        if (user == null || !user.getPassword().equals(password)) {
            ra.addFlashAttribute("loginFailed", true);
            return "redirect:users/login";
        }
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    private boolean userNotExists(User user) {
        return user == null;
    }

    private boolean userNoPermission(User user, String userId) {
        return !user.getUserId().equals(userId);
    }

    private boolean userPasswordNotMatching(User user, UserModifyCommand umc) {
        return !user.getPassword().equals(umc.getPassword());
    }
}
