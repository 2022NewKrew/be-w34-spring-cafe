package com.kakao.cafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/users/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        logger.info("[getUserProfile] userId = {}", userId);
        List<User> matchUsers = users.stream().filter(x -> x.getUserId().equals(userId)).collect(Collectors.toList());
        logger.info("matchUsers = {}", matchUsers);
        User matchUser = matchUsers.get(0);
        model.addAttribute("name", matchUser.getName());
        model.addAttribute("email", matchUser.getEmail());
        return "user/profile";
    }

    @GetMapping("/user/{id}/form")
    public String updateForm(@PathVariable String id, Model model) {
        logger.info("[updateForm] id = {}", id);
        User selectedUser = users.stream().filter(x -> x.getUserId().equals(id)).collect(Collectors.toList()).get(0);
        model.addAttribute("userId", selectedUser.getUserId());
        model.addAttribute("password", selectedUser.getPassword());
        model.addAttribute("name", selectedUser.getName());
        model.addAttribute("email", selectedUser.getEmail());
        return "user/updateForm";
    }
}
