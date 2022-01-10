package com.kakao.cafe;

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

    private List<User> userList = new ArrayList<>();

    @PostMapping("/user/create")
    public String signUp(User user) {
        userList.add(user);
        logger.info("userList = {}", userList);
        return "redirect:/";
    }

    @GetMapping("/user/form.html")
    public String form() {
        return "/user/form";
    }

    @GetMapping("/user/list.html")
    public String getUserList(Model model) {
        model.addAttribute("users", userList);
        return "/user/list";
    }

    @GetMapping("/user/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        User foundUser = null;
        for (User user : userList) {
            if (userId.equals(user.getUserId())) {
                foundUser = user;
                break;
            }
        }

        model.addAttribute("user", foundUser);
        return "/user/profile";
    }
}
