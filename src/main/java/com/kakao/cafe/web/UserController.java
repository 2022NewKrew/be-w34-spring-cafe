package com.kakao.cafe.web;

import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.web.dto.UserCreateRequest;
import com.kakao.cafe.web.dto.UserListResponse;
import com.kakao.cafe.web.dto.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/form")
    public String form() {
        return "/user/form";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute UserCreateRequest requestDto) {
        userService.create(requestDto.toEntity());
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll().stream()
                .map(UserListResponse::new)
                .collect(Collectors.toList()));
        return "/user/list";
    }

    @GetMapping("/users/{userId}")
    public String showUser(@PathVariable String userId, Model model) {
        model.addAttribute("user", new UserProfileResponse(userService.findById(userId)));
        return "/user/profile";
    }
}
