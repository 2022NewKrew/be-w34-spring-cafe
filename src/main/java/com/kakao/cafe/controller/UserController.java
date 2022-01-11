package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserForm;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/form")
    public String createForm(UserForm form) {
        return "user/form";
    }

    @PostMapping("/users")
    public String create(UserForm form) {
        User user = new User();
        user.setUserId(form.getUserId());
        user.setEmail(form.getEmail());
        user.setName(form.getName());
        user.setPassword(form.getPassword());
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findUserList(Model model) {
        List<User> users = userService.findUserList();
        model.addAttribute("users" , users);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String findUser(@PathVariable("userId") String userId, Model model) {
        User user = userService.findUser(userId).get();
        model.addAttribute("user" , user);
        return "user/profile";
    }
}
