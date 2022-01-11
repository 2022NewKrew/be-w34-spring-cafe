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
        List<UserDto.UserProfileResponse> readUserResponses = userService.readUsers();
        model.addAttribute("users", readUserResponses);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String userId, Model model) {
        UserDto.UserProfileResponse readUserResponse = userService.readUser(userId);
        model.addAttribute("user", readUserResponse);
        return "user/profile";
    }

    @GetMapping("/form")
    public String makeUserHtml() {
        return "user/form";
    }

    @PostMapping
    public String makeUser(@ModelAttribute("user") UserDto.CreateUserProfileRequest createUserProfileRequest) {
        userService.createUser(createUserProfileRequest);
        return "redirect:/users";
    }

    @GetMapping("/{userId}/form")
    public String updateUserHtml(@PathVariable("userId") String userId, Model model) {
        UserDto.UserProfileResponse userProfileResponse = userService.readUser(userId);
        model.addAttribute("user", userProfileResponse);
        return "user/updateForm";
    }

    @PostMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @ModelAttribute("user") UserDto.UpdateUserProfileRequest updateUserProfileRequest) {
        userService.updateUser(userId, updateUserProfileRequest);
        return "redirect:/users";
    }
}
