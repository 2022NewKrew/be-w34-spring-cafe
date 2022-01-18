package com.kakao.cafe.controller;

import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userListView(Model model) {
        List<UserDto> users = userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String profileView(@PathVariable String userId, Model model) {
        UserDto user = userService.filterUserById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/signup")
    public String signupView() {
        return "user/form";
    }

    @PostMapping("/signup")
    public String signup(UserDto user) {
        userService.signupUser(user);
        return "redirect:";
    }

    @GetMapping("/{userId}/update")
    public String updateView(@PathVariable String userId, HttpSession session, Model model) {
        UserDto user = userService.filterUserById(userId);
        UserDto loginUser = (UserDto) session.getAttribute("sessionedUser");
        if (loginUser != null && loginUser.getUserId().equals(user.getUserId())) {
            model.addAttribute("user", user);
            return "user/updateForm";
        }
        return "redirect:/users/login";
    }

    @PutMapping("/{userId}/update")
    public String update(UserDto user, String newPassword) {
        userService.updateUser(user, newPassword);
        return "redirect:";
    }

    @GetMapping("/login")
    public String loginView() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        UserDto user = userService.loginUser(userId, password);
        session.setAttribute("sessionedUser", user);
        return "redirect:/";
    }
}
