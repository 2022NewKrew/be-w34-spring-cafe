package com.kakao.cafe.web;

import com.kakao.cafe.dto.UserForm;
import com.kakao.cafe.dto.UserList;
import com.kakao.cafe.dto.UserProfile;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/form")
    public String createUserForm() {
        return "user/form";
    }

    @PostMapping("/user/create")
    public String createUser(@Valid @ModelAttribute UserForm userForm) {
        userService.join(userForm);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findUsers(Model model) {
        UserList users = userService.findUsers();
        model.addAttribute("users", users.getUserList());
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String findUserProfile(@PathVariable String userId, Model model) {
        UserProfile user = userService.findUserProfile(userId);
        model.addAttribute("user", user);
        return "user/profile";
    }
}