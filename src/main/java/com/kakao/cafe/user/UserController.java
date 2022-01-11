package com.kakao.cafe.user;

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
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("")
    public String processCreationForm(User user) {
        logger.info("Users before: {}", User.getUsers());
        User.addUser(user);
        logger.info("Users after: {}", User.getUsers());
        return "redirect:/users";
    }

    @GetMapping("")
    public String listUsers(Model model) {
        model.addAttribute("users", User.getUsers());
        return "/user/list";
    }

    @GetMapping("/{username}")
    public String showUser(@PathVariable String username, Model model) {
        model.addAttribute("user", User.findUserByUsername(username));
        return "/user/profile";
    }
}
