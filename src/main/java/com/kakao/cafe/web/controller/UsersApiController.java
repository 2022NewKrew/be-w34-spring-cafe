package com.kakao.cafe.web.controller;

import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersApiController {
    private final UserService userService = new UserService();

    @GetMapping
    String getUsers(Model model) {
        List<Map<String, String>> userList = new ArrayList<>();
        for (int i = 0; i < userService.getUserList().size(); i++) {
            Users user = userService.getByUserId(i);
            userList.add(
                    (Map.of("index", Integer.toString(i + 1),
                            "id", Integer.toString(user.getId()),
                            "userId", user.getUserId(),
                            "name", user.getName(),
                            "email", user.getEmail()))
            );
        }
        model.addAttribute("users", userList);
        return "users/list";
    }

    @GetMapping("/form")
    String getForm() {
        return "users/form";
    }

    @GetMapping("/login")
    String login() {
        return "users/login";
    }

    @GetMapping("/{id}")
    String findById(@PathVariable int id, Model model) {
        Users user = userService.getByUserId(id);
        model.addAttribute("user", user);
        return "users/profile";
    }

    @GetMapping("/profile")
    String getProfile(Users user, Model model) {
        model.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping
    String createUser(Users user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    String getUpdateForm(@PathVariable int id, Model model) {
        Users user = userService.getByUserId(id);
        model.addAttribute("user", user);
        return "users/updateForm";
    }

    @PostMapping("/update/{id}")
    String updateUserProfile(@PathVariable int id, String newPassword, Users updateUser) {
        userService.updateUser(id, updateUser, newPassword);
        return "redirect:/users";
    }
}
