package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
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
        User user = new User(userId, password, name, email);
        userService.signup(user);
        logger.info("POST request on Signup -> {}", user);
        return "redirect:users";
    }

    @GetMapping()
    public String getUserList(Model model) {
        Collection<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{targetUserId}")
    public String getUserProfile(@PathVariable("targetUserId") String targetUserId, Model model) {
        try {
            User user = userService.getUserFromUserId(targetUserId);
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
