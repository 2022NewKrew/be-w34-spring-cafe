package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.User.UserNoPassword;
import com.kakao.cafe.user.service.UserService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public String postSignup(String userId, String password, String name, String email) {
        final User user = new User(userId, password, name, email);
        userService.signup(user);
        LOGGER.info("POST request on Signup -> {}", user);
        return "redirect:users";
    }

    @GetMapping()
    public String getUserList(Model model) {
        final Collection<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserProfile(@PathVariable("userId") String userId, Model model) {
        final User user = userService.getUserByUserId(userId);
        final UserNoPassword userNoPassword = user.userNoPassword();
        model.addAttribute("user", userNoPassword);
        return "user/profile";
    }

    @GetMapping("/{userId}/form")
    public String getUserUpdate(@PathVariable("userId") String userId, Model model) {
        final User user = userService.getUserByUserId(userId);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PostMapping("/{userId}/form")
    public String postUserUpdate(@PathVariable("userId") String userId, String password, String name, String email) {
        final User user = userService.updateUser(userId, password, name, email);
        LOGGER.info("POST request on UpdateUser -> {}", user);
        return "redirect:/users/" + userId;
    }
}
