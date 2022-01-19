package com.kakao.cafe.user.controller;

import com.kakao.cafe.config.Login;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.user.dto.UserFormDto;
import com.kakao.cafe.user.dto.UserMapper;
import com.kakao.cafe.user.dto.UserProfileDto;
import com.kakao.cafe.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/form")
    public String showUpdateUserForm(@PathVariable String userId, Model model, @Login Optional<UserId> loginId) {
        User user = userService.findLoginUser(new UserId(userId), loginId);
        model.addAttribute("user", UserMapper.toUserDto(user));
        return "user/updateForm";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute UserFormDto userFormDto) {
        User user = UserMapper.toUser(userFormDto);
        userService.joinUser(user);
        return "redirect:/users";
    }

    @PutMapping("/user/update")
    public String updateUser(@ModelAttribute UserFormDto userFormDto) {
        User user = UserMapper.toUser(userFormDto);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findUsers(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", UserMapper.toListUserDto(users));
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String findUserProfile(@PathVariable String userId, Model model) {
        User user = userService.findUserByUserId(new UserId(userId));
        UserProfileDto userProfileDto = UserMapper.toUserProfileDto(user);
        model.addAttribute("user", userProfileDto);
        return "user/profile";
    }
}