package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserUpdateDto;
import com.kakao.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // form으로 작성된 정보를 받아서 user 객체 생성하고 저장
    @PostMapping("/create")
    public String create(UserDto user) {
        try {
            userService.signup(user);
        } catch (SQLException e) {
            logger.error("User : " + user, e);
            return "redirect:/";
        }

        return "redirect:/users/list";
    }

    @GetMapping("/list")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getUserList());

        return "/user/list";
    }

    @GetMapping("/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        UserProfileDto user;

        try {
            user = userService.findById(userId);
            model.addAttribute("user", user);
        } catch (NoSuchElementException e) {
            logger.error("", e);
            return "redirect:/";
        }

        return "/user/profile";
    }

    @GetMapping("/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model){
        UserProfileDto user;

        try {
            user = userService.findById(userId);
            model.addAttribute("user", user);
        } catch (NoSuchElementException e) {
            logger.error("", e);
            return "redirect:/";
        }

        return "/user/updateForm";
    }

    @PostMapping("/{userId}/update")
    public String update(@PathVariable String userId, UserUpdateDto userUpdateDto) {
        UserProfileDto newProfile = new UserProfileDto(userId, userUpdateDto.getEmail(), userUpdateDto.getName());

        try {
            userService.updateUserProfile(newProfile, userUpdateDto.getPassword());
        } catch (Exception e) {
            logger.error("UserProfileDto : " + newProfile, e);
        }

        return "redirect:/users/list";
    }
}
