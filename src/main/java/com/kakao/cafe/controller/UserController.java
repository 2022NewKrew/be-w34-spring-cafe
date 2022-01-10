package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserCreateDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserListDto;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/form")
    public String form() {
        return "user/form";
    }

    @PostMapping("/users")
    public String createUser(Model model, UserCreateDto userCreateDto) {
        userService.save(userCreateDto);
        return "redirect:users";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserDto> userDtoList = userService.getUserList();
        model.addAttribute("userListInfo", new UserListDto(userDtoList.size(), userDtoList));
        return "user/list";
    }

    @GetMapping("/users/{userId}")
    public String getUser(@PathVariable String userId, Model model) {
        model.addAttribute("user", userService.getUser(userId));
        return "user/profile";
    }
}
