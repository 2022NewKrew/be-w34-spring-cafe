package com.kakao.cafe.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String getUser(Model model) {
        model.addAttribute("users", userRepository.getUserList());
        return "user/list";
    }

    @GetMapping("/user/form")
    public String getUserForm() {
        return "user/form";
    }

    @GetMapping("/user/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        try {
            User user = userRepository.findUserWithId(userId);
            model.addAttribute("user", user);
        } catch(NoSuchElementException e) {
            logger.info("user/userid 실패: {}", e.getMessage());
            return "redirect:";
        }
        return "user/profile";
    }

    @PostMapping("/user/create")
    public String postUserCreate(User user) {
        logger.info("postUserCreate {}", user.toString());
        try {
            userRepository.addUser(user);
        } catch(IllegalArgumentException e) {
            logger.info("user/create 실패: {}", e.getMessage());
        }
        return "redirect:/user";
    }
}
