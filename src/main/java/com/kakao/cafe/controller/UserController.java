package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.domain.user.User;
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
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/form")
    public String getSignUp() {
        return "user/form";
    }

    @GetMapping(value="/user/login")
    public String getLogin(){
        return "user/login";
    }

    @GetMapping(value = "/user/list")
    public String getUserList(Model model) {
        model.addAttribute("userListSize", userService.getUserRepository().getUserList().size());
        model.addAttribute("userList", userService.getUserRepository().getUserList().getCopiedUserList());
        return "user/list";
    }

    @PostMapping(value = "/user/create")
    public String postSignUp(User user) {
        logger.info("user:{}", user);
        userService.getUserRepository().getUserList().add(user);
        logger.info("userList:{}", userService.getUserRepository().getUserList().getCopiedUserList());
        return "redirect:/user/list";
    }

    @PostMapping(value = "/user/login_check")
    public String postLoginCheck() {
        return "user/login_success";
    }

    @GetMapping(value = "user/profile/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        User user = userService.getUserRepository().getUserList().findByUserId(userId);
        logger.info("user:{}", user);
        model.addAttribute("user", user);
        return "/user/profile";
    }
}
