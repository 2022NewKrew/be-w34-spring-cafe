package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user")
    public String signUp(User user) {
        UserList.addUserList(user);
        logger.info("user create : {}", user.getUserId());
        return "redirect:/user/list";
    }

    @GetMapping("/user/form")
    public String openSignUp() {
        return "/user/form";
    }

    @GetMapping("/user/list")
    public String getUserList(Model model) {
        List<User> users = UserList.getUsers();
        logger.info("user list : {}", users);
        model.addAttribute("users", users);

        return "/user/list";
    }

    @GetMapping("/user/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        User user = UserList.getUserByUserId(userId);

        logger.info("get user profile : {}", user);
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @GetMapping("/user/{userId}/form")
    public String openUserProfile(@PathVariable String userId, Model model){
        User user = UserList.getUserByUserId(userId);

        model.addAttribute("user", user);
        return "/user/updateForm";
    }

    @PostMapping("/user/{userId}/form")
    public String updateUserProfile(@PathVariable String userId, User changedUser){
        User curUser = UserList.getUserByUserId(userId);
        logger.info("update profile success : {}", changedUser.getName());

        curUser.updateUser(changedUser);

        return "redirect:/user/list";
    }

}
