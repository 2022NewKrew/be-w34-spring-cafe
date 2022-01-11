package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.UserServiceImpl;
import com.kakao.cafe.vo.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String memberList(Model model) {
        List<User> userList = userService.getUsers();
        model.addAttribute("users", userList);
        return "user/list";
    }

    @GetMapping("/form")
    public String createUser() {
        return "user/form";
    }

    @PostMapping("/create")
    public String createUser(User user) {
        userService.join(user);
        return "redirect:/users";
    }
}