package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserProfileResponse;
import com.kakao.cafe.dto.UserSignUpRequest;
import com.kakao.cafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;
    private static final List<User> users = new ArrayList<>();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String list(Model model) {
        log.info("start list()");
        model.addAttribute("users", users);
        return "/users/list";
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String signUp(UserSignUpRequest request) {
        log.info("start signUp()");
        userService.signUp(request, users);
        return "redirect:/users";
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String signUpJson(@RequestBody UserSignUpRequest request) {
        log.info("start signUp()");
        userService.signUp(request, users);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String profile(@PathVariable int userId, Model model) {
        UserProfileResponse userProfile = userService.profile(userId, users);
        model.addAttribute("userProfile", userProfile);
        return "/users/profile";
    }

}
