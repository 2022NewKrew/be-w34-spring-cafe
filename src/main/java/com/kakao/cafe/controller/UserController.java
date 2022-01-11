package com.kakao.cafe.controller;

import com.kakao.cafe.model.domain.User;
import com.kakao.cafe.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String goRegisterView() {
        return "user/register";
    }

    @PostMapping("/register")
    public String userRegister(User user) {
        userService.registerUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/view/{id}")
    public String userView(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", userService.findUserByID(id));
        return "user/view";
    }
}
