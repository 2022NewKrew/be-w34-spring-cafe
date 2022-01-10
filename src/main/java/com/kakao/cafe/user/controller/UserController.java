package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(UserController.class);

//    @GetMapping("/user/form")
//    public String createUser() {
//        return "user/form";
//    }

    @PostMapping("/user/create")
    public String createUser(User user) {
        logger.info(String.valueOf(user));
        logger.info(user.getUserId());
        logger.info(user.getEmail());
        logger.info(user.getName());
        logger.info(user.getPassword());
        users.add(user);
        logger.info("{}", users);
//        new User(user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
//        users.add(user);


        return "redirect:/";
    }

    @GetMapping("/user/list")
    public String memberList(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
        return "user/list";
    }
}
