package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserProfileResponse;
import com.kakao.cafe.dto.UserSignUpRequest;
import com.kakao.cafe.dto.UserUpdateRequest;
import com.kakao.cafe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String list(Model model) {
        log.info("start list()");
        model.addAttribute("users", userService.list());
        return "/users/list";
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String signUp(UserSignUpRequest request) {
        log.info("start signUp()");
        userService.signUp(request);
        return "redirect:/users";
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String signUpJson(@RequestBody UserSignUpRequest request) {
        log.info("start signUp()");
        userService.signUp(request);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String profile(@PathVariable int id, Model model) {
        log.info("start profile()");
        User user = userService.profile(id);
        model.addAttribute("userProfile", UserProfileResponse.from(user));
        return "/users/profile";
    }

    @GetMapping("/users/{id}/form")
    public String getUpdateForm(@PathVariable int id, Model model) {
        log.info("start getUpdateForm()");
        User user = userService.profile(id);
        model.addAttribute("userProfile", UserProfileResponse.from(user));
        return "/users/updateForm";
    }

    @PostMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateUser(@PathVariable int id, UserUpdateRequest request, Model model) {
        log.info("start updateUser()");
        if (userService.updateUser(id, request)) {
            return "redirect:/users";
        }

        User user = userService.profile(id);
        model.addAttribute("userProfile", UserProfileResponse.from(user));
        model.addAttribute("isFailed", true);
        return "/users/updateForm";
    }

}
