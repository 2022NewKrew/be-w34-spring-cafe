package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.dto.user.LoginDto;
import com.kakao.cafe.dto.user.SignupDto;
import com.kakao.cafe.dto.user.UserDto;

import com.kakao.cafe.service.UserService;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public String requestSignup(@Valid SignupDto signupDto) {
        userService.signUp(signupDto);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String requestUserList(Model model) {
        List<UserDto> userList = userService.getUserList();
        model.addAttribute("users", userList);
        return "users/list";
    }

    @GetMapping("/uesrs/{userId}")
    public String requestUserProfile(@PathVariable UUID userId, Model model) {
        UserDto user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "users/profile";
    }

    //로그인
    @PostMapping("/login")
    public String login(@Valid LoginDto dto, HttpSession session) {
        UserName userName = new UserName(dto.getUserName());
        Password password = new Password(dto.getPassword());
        UserDto userDto = userService.findUserByLoginInfo(userName, password);
        session.setAttribute("loggedInUser", userDto);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
