package com.kakao.cafe.web;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.web.dto.UserCreateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/form")
    public String getUserForm() {
        return "user/form";
    }

    @PostMapping("/create")
    public String createUser(String userId, String password, String name, String email) {
        userService.create(new UserCreateRequestDto(userId, password, name, email));
        return "redirect:/user";
    }

    @GetMapping
    public String getUserList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserByUserId(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.findByUserId(userId));
        return "user/profile";
    }
}
