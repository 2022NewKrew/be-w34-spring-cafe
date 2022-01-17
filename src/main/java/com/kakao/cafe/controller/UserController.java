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

    @GetMapping("/{userId}/form")
    public String updateUserHtml(@PathVariable("userId") String userId, HttpSession session, Model model) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        userService.validateAuthForUpdateUser(sessionedUser.getUserId(), userId);

        UserDto.UserProfileResponse userProfileResponse = userService.readUser(userId);
        model.addAttribute("user", userProfileResponse);
        return "user/updateForm";
    }

    @PostMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @ModelAttribute("user") UserDto.UpdateUserProfileRequest updateUserProfileRequest,
                             HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        userService.updateUser(userId, updateUserProfileRequest, sessionedUser.getUserId());
        return "redirect:/users";
    }
}
