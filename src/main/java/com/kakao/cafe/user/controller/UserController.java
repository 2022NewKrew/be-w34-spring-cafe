package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.User;
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
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public String postSignup(String userId, String password, String name, String email) {
        final User user = new User(userId, password, name, email);
        userService.signup(user);
        logger.info("POST request on Signup -> {}", user);
        return "redirect:users";
    }

    @GetMapping()
    public String getUserList(Model model) {
        final Collection<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{targetUserId}")
    public String getUserProfile(@PathVariable("targetUserId") String userId, Model model) {
        try {
            final User user = userService.getUserFromUserId(userId);
            model.addAttribute("userId", user.getUserId());
            model.addAttribute("usename", user.getName());
            model.addAttribute("email", user.getEmail());
            return "user/profile";
        } catch (ResponseStatusException e) {
            logger.warn("[ERROR] 사용자를 찾을 수 없습니다.");
            return "redirect:/error/no-page";
        }
    }
}
