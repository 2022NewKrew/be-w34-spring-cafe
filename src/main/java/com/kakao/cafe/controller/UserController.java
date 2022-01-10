package com.kakao.cafe.controller;

import com.kakao.cafe.dto.User;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/create")
    public String create(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ) {
        userService.create(userId, password, name, email);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String list(Model model) {
        List<User> users = userService.list()
                .stream()
                .map(com.kakao.cafe.entity.User::toDto)
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/{id}")
    public String profile(@PathVariable("id") String id, Model model) {
        var entity = userService.get(id);
        if (entity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        User user = entity.toDto();
        model.addAttribute("user", user);
        return "profile";
    }
}
