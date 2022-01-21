package com.kakao.cafe.web;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.dto.user.*;

import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        AtomicLong index = new AtomicLong(1);
        List<UserListShow> userList = userService.getAllUsers().stream()
                .map(user -> new UserListShow(index.getAndIncrement(), user))
                .collect(Collectors.toUnmodifiableList());
        model.addAttribute("users", userList);
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
    public String updateForm(@PathVariable String userId, Model model) {
        UserInfo userInfo = userService.getUserInfo(userId);
        model.addAttribute("user", userInfo);
        return "user/updateForm";
    }

    @PutMapping("/users/{userId}/update")
    public String updateUser(@PathVariable String userId,
                             @SessionAttribute("sessionedUser") User user,
                             UserModifyCommand umc,
                             RedirectAttributes ra) {
        if (userPasswordNotMatching(user, umc.getPassword())) {
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
        if (user == null || userPasswordNotMatching(user, password)) {
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

    private boolean userPasswordNotMatching(User user, String password) {
        return !user.getPassword().equals(password);
    }
}
