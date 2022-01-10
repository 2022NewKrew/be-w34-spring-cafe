package com.kakao.cafe.web;

import com.kakao.cafe.domain.user.User;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final List<User> userList = new ArrayList<>();

    @GetMapping("/users")
    public String printUsers(Model model) {
        model.addAttribute("users", userList);
        return "user/list";
    }

    @PostMapping("/users")
    public String addUser(User user) {
        userList.add(user);
        return "redirect:users/";
    }

    @GetMapping("/users/{userId}")
    public String printProfile(@PathVariable String userId, Model model) {
        User target = findUserWithId(userId);
        model.addAttribute("name", target.getName());
        model.addAttribute("email", target.getEmail());
        return "user/profile";
    }

    private User findUserWithId(String userId) {
        Optional<User> matchingUser = userList.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
        if (matchingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return matchingUser.get();
    }
}
