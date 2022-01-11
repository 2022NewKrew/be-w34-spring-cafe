package com.kakao.cafe.web;

import com.kakao.cafe.domain.users.Users;
import com.kakao.cafe.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersApiController {
    private final UserService userService = new UserService();

    @GetMapping
    public String getUsers(Model model) {
        List<Users> userList = userService.getUserList();
        model.addAttribute("users", userList);
        return "users/list";
    }

    @GetMapping("/form")
    public String getForm() {
        return "users/form";
    }

    @GetMapping("/login")
    public String login() {
        return "users/login";
    }


    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Users user =  userService.getByUserId(id);
        model.addAttribute("user", user);
        return "users/profile";
    }

    @GetMapping("/profile")
    public String getProfile(Users user, Model model) {
        model.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping
    String createUser(Users user) {
        userService.addUser(user);
        return "redirect:/users";
    }
}
