package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.LoginUser;
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

    @GetMapping("/updateform")
    public String updateUserHtml(@LoginUser UserDto.UserSessionDto loginUser, Model model) {
        UserDto.UserProfileForUpdateReponse userProfileResponse = userService.readUserForUpdate(loginUser.getUserId());
        model.addAttribute("user", userProfileResponse);
        return "user/updateForm";
    }

    @PostMapping
    public String updateUser(@ModelAttribute("user") UserDto.UpdateUserProfileRequest updateUserProfileRequest,
                             @LoginUser UserDto.UserSessionDto loginUser) {
        userService.updateUser(loginUser.getUserId(), updateUserProfileRequest);
        return "redirect:/users";
    }
}
