package com.kakao.cafe.user.controller;

import com.kakao.cafe.exception.InvalidFormatException;
import com.kakao.cafe.user.dto.LoggedInUser;
import com.kakao.cafe.user.dto.UserEditForm;
import com.kakao.cafe.user.dto.UserLoginForm;
import com.kakao.cafe.user.service.LoginService;
import com.kakao.cafe.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final UserService userService;
    private final LoginService loginService;

    public LoginController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Validated UserLoginForm userLoginForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        LoggedInUser loggedInUser = loginService.login(userLoginForm);
        session.setAttribute("loggedInUser", loggedInUser);
        return "redirect:/";
    }

    @DeleteMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/user/edit")
    public String showProfileEditor(HttpSession session, Model model) {
        LoggedInUser loggedInUser = loginService.getLoggedInUser(session);
        model.addAttribute("user", userService.getUserViewByUsername(loggedInUser.getUsername()));
        return "user/edit-form";
    }

    @PostMapping("/user/edit")
    public String editProfile(@Validated UserEditForm userEditForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            throw new InvalidFormatException();
        }
        LoggedInUser loggedInUser = loginService.getLoggedInUser(session);
        userService.updateUser(loggedInUser.getId(), userEditForm);
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String showLoggedInUserProfile(HttpSession session) {
        LoggedInUser loggedInUser = loginService.getLoggedInUser(session);
        return "redirect:/users/" + loggedInUser.getUsername();
    }
}
