package com.kakao.cafe.user.controller;

import com.kakao.cafe.user.dto.request.UserFormReqDto;
import com.kakao.cafe.user.dto.response.UserResDto;
import com.kakao.cafe.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        List<UserResDto> users = userService.getUsers();
        model.addAttribute("users", users);
        return "/user/list";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute UserFormReqDto userFormReqDto) {
        // signup and redirect
        userService.signup(userFormReqDto);
        return "redirect:/users/" + userFormReqDto.getUserId();
    }

    @GetMapping("/{userId}")
    public String getProfile(@PathVariable String userId, Model model) {
        UserResDto user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @GetMapping("/{userId}/form")
    public String getUserForm(@PathVariable String userId, Model model) {
        UserResDto user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "/user/updateForm";
    }

    @PostMapping("/{userId}")
    public String updateUser(@PathVariable String userId, @ModelAttribute UserFormReqDto userFormReqDto) {
        userService.updateUser(userId, userFormReqDto);
        return "redirect:/users";
    }

    @PostMapping("/signin")
    public String login(String userId, String password, HttpSession session) {
        userService.login(userId, password);
        session.setAttribute("sessionUserId", userId);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
