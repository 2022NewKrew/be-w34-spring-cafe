package com.kakao.cafe.web;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserFormDto;
import com.kakao.cafe.dto.UserMapper;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/form")
    public String createUserForm() {
        return "user/form";
    }

    @PostMapping("/user/create")
    public String createUser(UserFormDto userFormDto) {
        User user = UserMapper.toUser(userFormDto);
        userService.join(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String findUsers(Model model) {
        List<User> users = userService.findUsers();
        List<UserDto> userlist = UserMapper.toListUserDto(users);
        model.addAttribute("users", userlist);
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String findUserProfile(@PathVariable String userId, Model model) {
        User user = userService.findUserByUserId(userId);
        UserProfileDto userProfileDto = UserMapper.toUserProfileDto(user);
        model.addAttribute("user", userProfileDto);
        return "user/profile";
    }
}