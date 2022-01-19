package com.kakao.cafe.user.controller;

import com.kakao.cafe.config.Login;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.user.dto.UserDetailDto;
import com.kakao.cafe.user.dto.UserFormDto;
import com.kakao.cafe.user.dto.UserMapper;
import com.kakao.cafe.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", UserMapper.toListUserDto(users));
        return "user/list";
    }

    @GetMapping("/user/{userId}")
    public String findUserProfile(@PathVariable String userId, Model model) {
        User user = userService.findByUserId(new UserId(userId));
        UserDetailDto userDetailDto = UserMapper.toUserProfileDto(user);
        model.addAttribute("user", userDetailDto);
        return "user/profile";
    }

    @GetMapping("/user/{userId}/form")
    public String showUpdateUserForm(@PathVariable String userId, Model model, @Login UserId loginId) {
        UserId updateId = new UserId(userId);
        User user = userService.findByLoginUserId(updateId, loginId);
        model.addAttribute("user", UserMapper.toUserDto(user));
        return "user/updateForm";
    }

    @PostMapping("/user/create")
    public String joinUser(@ModelAttribute UserFormDto userFormDto) {
        User user = UserMapper.toUser(userFormDto);
        userService.joinUser(user);
        return "redirect:/users";
    }

    @PutMapping("/user/modify")
    public String modifyUser(@ModelAttribute UserFormDto userFormDto, @Login UserId loginId) {
        User user = UserMapper.toUser(userFormDto);
        userService.updateUser(user, loginId);
        return "redirect:/users";
    }
}