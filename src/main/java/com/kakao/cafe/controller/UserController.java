package com.kakao.cafe.controller;

import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.domain.UserDto.UserNoPassword;
import com.kakao.cafe.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public String postSignup(String userId, String password, String name, String email) {
        final UserDto userDto = new UserDto(userId, password, name, email);
        userService.signup(userDto);
        LOGGER.info("POST request on Signup -> {}", userDto);
        return "redirect:users";
    }

    @GetMapping()
    public String getUserList(Model model) {
        final List<UserDto> users = userService.getUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserProfile(@PathVariable("userId") String userId, Model model) {
        final UserDto userDto = userService.getUserByUserId(userId);
        final UserNoPassword userNoPassword = userDto.userNoPassword();
        model.addAttribute("user", userNoPassword);
        return "user/profile";
    }

    @GetMapping("/{userId}/form")
    public String getUserUpdate(@PathVariable("userId") String userId, Model model) {
        final UserDto userDto = userService.getUserByUserId(userId);
        model.addAttribute("user", userDto);
        return "user/updateForm";
    }

    @PostMapping("/{userId}/form")
    public String postUserUpdate(@PathVariable("userId") String userId, String password, String name, String email) {
        final UserDto userDto = new UserDto(userId, password, name, email);
        final UserDto updatedUserDto = userService.updateUser(userDto);
        LOGGER.info("POST request on UpdateUser -> {}", updatedUserDto);
        return "redirect:/users/" + userId;
    }
}
