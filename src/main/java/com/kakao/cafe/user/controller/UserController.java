package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.UserDto;
import com.kakao.cafe.user.dto.UserProfileDto;
import com.kakao.cafe.user.dto.UserRequest;
import com.kakao.cafe.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public String getUserLists(Model model){
        List<UserDto> usersList = userService.getUsersList();
        model.addAttribute("users", usersList);

        return "/user/list";
    }

    @GetMapping("/profile/{userId}")
    public String getUserProfilePage(@PathVariable String userId, Model model){
        UserProfileDto userProfile = userService.getUserProfile(userId);
        model.addAttribute("profile", userProfile);

        return "/user/profile";
    }

    @PostMapping("/create")
    public String signUp(UserRequest userRequest){
        userService.signUp(userRequest);

        return "redirect:/user/list";
    }

}
