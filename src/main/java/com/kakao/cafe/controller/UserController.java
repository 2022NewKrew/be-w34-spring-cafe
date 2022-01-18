package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.file.AccessDeniedException;
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

    @GetMapping("/{userId}/updateform")
    public String updateUserByUserIdHtml(@PathVariable("userId") String userId, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        userService.validateAuthForUpdateUser(userId, sessionedUser.getUserId());

        return "redirect:/users/updateform";
    }

    @GetMapping("/updateform")
    public String updateUserHtml(HttpSession session, Model model) {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");

        UserDto.UserProfileResponse userProfileResponse = userService.readUser(sessionedUser.getUserId());
        model.addAttribute("user", userProfileResponse);
        return "user/updateForm";
    }

    @PostMapping
    public String updateUser(@ModelAttribute("user") UserDto.UpdateUserProfileRequest updateUserProfileRequest, HttpSession session) {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        userService.updateUser(sessionedUser.getUserId(), updateUserProfileRequest);
        return "redirect:/users";
    }
}
