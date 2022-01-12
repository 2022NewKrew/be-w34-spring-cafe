package com.kakao.cafe.web;

import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.ShowUserDto;
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
    public String userForm() {
        return "/user/form";
    }

    @PostMapping("/users")
    public String userCreate(CreateUserDto createUserDto) {
        userService.save(createUserDto);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        List<ShowUserDto> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "/user/list";
    }

    @GetMapping("/user/profile")
    public String userProfile() {
        return "/user/profile";
    }

    @GetMapping("/users/{userId}")
    public String userListProfile(Model model, @PathVariable String userId) {
        ShowUserDto user = userService.findById(userId);
        model.addAttribute("user", user);
        return "/user/profile";
    }
}
