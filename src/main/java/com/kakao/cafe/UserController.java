package com.kakao.cafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final List<User> users = new ArrayList<>();

    @PostMapping("/user/create")
    public String postUserCreate(String userId, String password, String name, String email) {
        logger.info("[postUserCreate] userId = {}, password = {}, name = {}, email = {}", userId, password, name, email);
        users.add(new User(userId, password, name, email));
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        logger.info("[getUsers]");
        List<Map<String, String>> userList = new ArrayList<>();
        for(int i = 0; i < users.size(); i++) {
            userList.add(Map.of("index", Integer.toString(i+1),
                    "userId", users.get(i).getUserId(),
                    "name", users.get(i).getName(),
                    "email", users.get(i).getEmail())
                    );
        }
        model.addAttribute("users", userList);
        return "user/list";
    }
}
