package com.kakao.cafe.controller;

import com.kakao.cafe.model.User;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = new UserServiceImp();

    @GetMapping("/user/list")
    public String getUsers(Model model){
        logger.info("Controller : UserController, Method : getUsers, Users : {}", userService.readUsers());

        model.addAttribute("users", userService.readUsers());
        return "user/list";
    }

    @PostMapping("/user/create")
    public String createUser(User user){
        logger.info("Controller : UserController, Method : createUser, Users : {}", user);
        userService.addUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("user/profile/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model){
        logger.info("Controller : UserController, Method : getUserProfile, User Id : {}", userId);
        model.addAttribute("user", userService.readUser(userId));
        return "user/profile";
    }
}
