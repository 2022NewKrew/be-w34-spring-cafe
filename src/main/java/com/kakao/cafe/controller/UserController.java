package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    String users(Model model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("users", userList);

        return "user/list";
    }

    @GetMapping("/form")
    String form() {

        return "user/form";
    }

    @GetMapping("/create")
    String create(User user) {
        userService.insertUser(user);
        logger.info("create User : Email : {}, Name : {}", user.getEmail(), user.getName());

        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    String profile(@PathVariable String userId, Model model) {
        User user = userService.getUserByUserId(userId);
        model.addAttribute("user", user);

        return "user/profile";
    }

}
