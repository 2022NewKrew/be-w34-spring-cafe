package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Resource(name = "userService")
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    String getUsers(Model model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("users", userList);

        return "user/list";
    }

    @PostMapping("/create")
    String createUser(User user) {
        userService.insertUser(user);
        logger.info("create User -> Email : {}, Name : {}", user.getEmail(), user.getName());

        return "redirect:/users";
    }

    @GetMapping("/{userId}/form")
    String getUserForm(@PathVariable String userId, Model model) {
        User user = userService.getUserByUserId(userId);
        model.addAttribute("user", user);
        return "user/updateForm";
    }

    @PostMapping("/{userId}/update")
    String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    String getUserProfile(@PathVariable String userId, Model model) {
        User user = userService.getUserByUserId(userId);
        model.addAttribute("user", user);

        return "user/profile";
    }

}
