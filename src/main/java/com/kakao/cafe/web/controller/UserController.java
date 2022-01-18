package com.kakao.cafe.web.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class UserController {
    private final UserService userService;

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
        model.addAttribute("userListSize", userService.getUserListSize());
        model.addAttribute("userList", userService.getUserList());
        return "user/list";
    }

    @PostMapping(value = "/user/create")
    public String postSignUp(String userId,String password,String email) {
        userService.createUser(userId,password,email);
        log.info("userList:{}", userService.getUserList());
        return "redirect:/user/list";
    }

    @PostMapping(value = "/user/login_check")
    public String postLoginCheck() {
        return "user/login_success";
    }

    @GetMapping(value = "user/profile/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        User user = userService.getUserByUserId(userId);
        log.info("user:{}", user);
        model.addAttribute("user", user);
        return "/user/profile";
    }
}
