package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public String getUser(Model model) {
        try {
            model.addAttribute("users", userRepository.getUserList());
        } catch(Exception e) {
            logger.info("users 실패: {}", e.getMessage());
        }
        return "users/list";
    }

    @GetMapping("/users/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        try {
            User user = userRepository.findUserWithId(userId);
            model.addAttribute("user", user);
        } catch(Exception e) {
            logger.info("users/userid 실패: {}", e.getMessage());
            return "redirect:/";
        }
        return "users/profile";
    }

    @PostMapping("/users")
    public String postUserCreate(User user) {
        try {
            userRepository.addUser(user);
        } catch(Exception e) {
            logger.info("POST /users 실패: {}", e.getMessage());
        }
        return "redirect:/users";
    }
}
