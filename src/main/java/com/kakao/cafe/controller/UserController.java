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
            logger.error("/users/create, user signup failed. UserDto = {}", user, e);
            return "redirect:/";
        }
        logger.info("/users/create, user created. id = {}", user.getUserId());

        return "redirect:/users/list";
    }

    // 모든 유저 리스트를 가져와서 표시
    @GetMapping("/list")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getUserList());

        return "/user/list";
    }

    // 해당 id의 유저의 프로필을 찾아서 프로필 화면 표시
    @GetMapping("/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        UserProfileDto user;

        try {
            user = userService.findById(userId);
            model.addAttribute("user", user);
        } catch (NoSuchElementException e) {
            logger.error("/users/{userId}, userId = {}. User does not exist.", userId, e);
            return "redirect:/";
        }
        logger.info("/users/{userId}, User(id = {}) founded.", userId);

        return "/user/profile";
    }

    // 사용자 리스트에서 수정 버튼을 누르면, 해당 사용자의 회원정보 수정 화면으로 이동
    @GetMapping("/{userId}/form")
    public String updateForm(@PathVariable String userId, Model model){
        UserProfileDto user;

        try {
            user = userService.findById(userId);
            model.addAttribute("user", user);
        } catch (NoSuchElementException e) {
            logger.error("/users/{userId}/form, userId = {}. User does not exist.", userId, e);
            return "redirect:/";
        }

        return "/user/updateForm";
    }

    // 회원정보 수정하고, 수정 버튼을 눌렀을 때
    @PostMapping("/{userId}/update")
    public String update(@PathVariable String userId, UserUpdateDto userUpdateDto) {
        UserProfileDto newProfile = new UserProfileDto(userId, userUpdateDto.getEmail(), userUpdateDto.getName());

        try {
            userService.updateUserProfile(newProfile, userUpdateDto.getPassword());
        } catch (NoSuchElementException e) {
            logger.error("/users/{userId}/update, User(id = {}) failed to update Profile. User does not exist.", userId, e);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            logger.error("/users/{userId}/update, User(id = {}) failed to update Profile. Incorrect password.", userId, e);
        }
        logger.info("/users/{userId}/update, User(id = {}) updated profile.", userId);

        return "redirect:/users/list";
    }
}
