package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.UserDto;
import com.kakao.cafe.user.dto.UserProfileDto;
import com.kakao.cafe.user.dto.UserRequest;
import com.kakao.cafe.user.exception.UserInfoMismatchException;
import com.kakao.cafe.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/update/{userId}")
    public String getUserUpdateFormPage(@PathVariable String userId, HttpSession session){
        if (!((session.getAttribute("LOGIN_USER_ID")).equals(userId))) {
            throw new UserInfoMismatchException();
        }
        return "/user/updateForm";
    }

    @PutMapping("/update")
    public String updateUser(UserRequest userRequest){
        userService.updateUser(userRequest);
        return "redirect:/user/list";
    }
}
