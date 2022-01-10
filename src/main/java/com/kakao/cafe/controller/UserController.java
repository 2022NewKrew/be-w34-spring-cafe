package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserForm;
import com.kakao.cafe.entiry.User;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public String create(UserForm form) {
        logger.info("POST /users");
        User newUser = User.of(form);
        userService.join(newUser);
        return "redirect:/users";
    }

    @GetMapping("")
    public String list(Model model) {
        logger.info("GET /users");
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String profile(@PathVariable String userId, Model model){
        logger.info("GET /users/{userId}");
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }


}
