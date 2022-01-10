package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findUsers(Model model) {
        List<UserDto.userProfileResponse> readUserResponses = userService.readUsers();
        model.addAttribute("users", readUserResponses);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String userId, Model model) {
        UserDto.userProfileResponse readUserResponse = userService.readUser(userId);
        model.addAttribute("user", readUserResponse);
        return "user/profile";
    }

    @GetMapping("/form")
    public String makeUserHtml() {
        return "user/form";
    }

    @PostMapping
    public String makeUser(@RequestParam("userId") String userId, @RequestParam("password") String password, @RequestParam("name") String name, @RequestParam("email") String email) {
        userService.createUser(userId, password, email, name);
        return "redirect:/users";
    }
}
