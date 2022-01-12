package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserRegisterDto;
import com.kakao.cafe.dto.UserUpdateDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getUserElementDtos());
        return "user/list";
    }

    @PostMapping
    public String register(UserRegisterDto userRegisterDto) {
        userService.createUser(userRegisterDto);
        return "redirect:/users";
    }

    @GetMapping("/{userId}")
    public String showUserProfile(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "user/profile";
    }

    @GetMapping("/{userId}/update")
    public String showUpdateUserInformation(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "user/updateForm";
    }

    @PostMapping("/{userId}/update")
    public String updateUserInformation(@PathVariable String userId, UserUpdateDto userUpdateDto) {
        userService.updateUser(userId, userUpdateDto);
        return "redirect:/users";
    }
}
