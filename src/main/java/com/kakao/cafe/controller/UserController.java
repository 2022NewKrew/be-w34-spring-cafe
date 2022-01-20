package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserRequestDto;
import com.kakao.cafe.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);
        return "redirect:/users";
    }

    @GetMapping
    public String getUserList(Model model) {
        List<UserDto> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "user/list";
    }

    @GetMapping("/{userId}")
    public String getUserInfo(@PathVariable("userId") String userId, Model model) {
        UserDto user = userService.findById(userId);
        if (user == null) {
            return "error/userNotExist";
        }
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/login")
    public String login(UserRequestDto userRequestDto, HttpSession httpSession, Model model) {
        try{
            userService.login(userRequestDto, httpSession);
        }
        catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        userService.logout(httpSession);
        return "redirect:/";
    }
}
