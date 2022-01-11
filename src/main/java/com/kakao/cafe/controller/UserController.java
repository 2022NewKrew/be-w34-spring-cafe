package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String index(Model model){
        List<Object> posts = new ArrayList<>();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/users/form")
    public String signupForm(){
        return "/users/form";
    }

    @PostMapping("/users")
    public String signup(User user){
        logger.info(user.toString());
        UserList userList = UserList.getInstance();
        userList.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String viewUserList(Model model){
        logger.info("viewUserList");
        UserList userList = UserList.getInstance();
        model.addAttribute("users", userList);
        return "/users/list";
    }

    @GetMapping("/users/{userId}")
    public String viewProfile(@PathVariable String userId, Model model){
        User user = UserList.findByUserId(userId);
        if(user == null) {
            logger.info(userId + ": NULL");
            model.addAttribute("users", UserList.getInstance());
            return "/users";
        }
        model.addAttribute("user", user);
        return "/users/profile";
    }
}
