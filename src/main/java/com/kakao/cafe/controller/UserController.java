package com.kakao.cafe.controller;

import com.kakao.cafe.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final List<User> userList = new ArrayList<>();

    // form으로 작성된 정보를 받아서 user 객체 생성하고 저장
    @PostMapping("/create")
    public String create(User user) {
        user.setIndex();
        userList.add(user);
        logger.info("userList = {}", userList);
        return "redirect:/";
    }

    @GetMapping("/list.html")
    public String getUserList(Model model) {
        model.addAttribute("users", userList);
        return "/user/list";
    }

    @GetMapping("/{userId}")
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
