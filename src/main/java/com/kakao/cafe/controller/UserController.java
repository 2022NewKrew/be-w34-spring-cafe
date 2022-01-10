package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users" , users);
        return "user/list";
    }
}
