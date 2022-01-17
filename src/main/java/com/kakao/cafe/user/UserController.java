package com.kakao.cafe.user;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserDto;
import com.kakao.cafe.user.dto.UserFormDto;
import com.kakao.cafe.user.dto.UserMapper;
import com.kakao.cafe.user.dto.UserProfileDto;
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

    @GetMapping("/user/form")
    public String showUserForm() {
        return "user/form";
    }

    @GetMapping("/users/{userId}/form")
    public String showUpdateUserForm(@PathVariable String userId, Model model) {
        User user = userService.findUserByUserId(userId);
        UserDto userDto = UserMapper.toUserDto(user);
        model.addAttribute("user", userDto);
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