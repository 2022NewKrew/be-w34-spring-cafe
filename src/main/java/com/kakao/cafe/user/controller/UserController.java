package com.kakao.cafe.user.controller;

import com.kakao.cafe.exception.InvalidFormatException;
import com.kakao.cafe.user.dto.LoggedInUser;
import com.kakao.cafe.user.dto.UserEditForm;
import com.kakao.cafe.user.interceptor.NeedLogin;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @NeedLogin
    @GetMapping("")
    public String showLoggedInUserProfile(@RequestAttribute LoggedInUser loggedInUser) {
        // Currently, no special private profile page
        // Just reuse the public profile page
        return "redirect:/users/" + loggedInUser.getUsername();
    }

    @NeedLogin
    @GetMapping("/edit")
    public String showProfileEditor() {
        return "user/edit-form";
    }

    @NeedLogin
    @PostMapping("/edit")
    public String editProfile(@Validated UserEditForm userEditForm, BindingResult bindingResult,
                              @RequestAttribute LoggedInUser loggedInUser) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        userService.updateUser(loggedInUser.getId(), userEditForm);
        return "redirect:/user";
    }
}
