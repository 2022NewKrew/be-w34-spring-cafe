package com.kakao.cafe.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController {
    List<User> users = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/users")
    public String processCreationForm(User user) {
        logger.info("User before new registration: " + users);
        users.add(user);
        logger.info("User after new registration: " + users);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", users);
        return "/user/list";
    }

    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", findUserById(userId));
        return "/user/profile";
    }

    private User findUserById(String userId) {
        return users.stream().filter(user -> user.getId().equals(userId)).findFirst().orElseThrow(NoSuchElementException::new);
    }
}
