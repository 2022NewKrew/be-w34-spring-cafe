package com.kakao.cafe.controller;

import com.kakao.cafe.dto.ProfileResponseDto;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.SignupRequestDto;
import com.kakao.cafe.dto.UserListResponseDto;
import com.kakao.cafe.mapper.UserMapper;

import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<User> userList = userService.getUserList();
        List<UserListResponseDto> dtoList = userList.stream()
                .map(userMapper::userToUserListResponseDto)
                .collect(Collectors.toList());
        model.addAttribute("users", dtoList);
        return "users/list";
    }

    @PostMapping("/users")
    public String requestSignup(@Valid SignupRequestDto dto) {
        User user = userMapper.signupRequestDtoToUser(dto);
        userService.registerUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String requestUserList(Model model) {
        List<User> userList = userService.getUserList();
        List<UserListResponseDto> dtoList = userList.stream()
                .map(userMapper::userToUserListResponseDto)
                .collect(Collectors.toList());
        model.addAttribute("users", dtoList);
        return "users/list";
    }

    @GetMapping("/users/{userId}")
    public String requestUserProfile(@PathVariable String userId, Model model) {
        User user = userService.findUserById(userId);
        ProfileResponseDto dto = userMapper.userToProfileResponseDto(user);
        model.addAttribute("user", dto);
        return "users/profile";
    }
}
