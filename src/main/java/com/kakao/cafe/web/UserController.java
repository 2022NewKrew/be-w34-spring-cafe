package com.kakao.cafe.web;

import com.kakao.cafe.model.User;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String userRegist(User user) {
        userService.registerUser(user);
        return "redirect:/users";
    }

    @GetMapping
    public String getUserList(Model model) {
        logger.info("Display users' List");
        List<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserInfo(@PathVariable("userId") String userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }
}
