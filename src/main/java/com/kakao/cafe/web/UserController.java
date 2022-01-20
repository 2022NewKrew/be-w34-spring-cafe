package com.kakao.cafe.web;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.web.dto.UserCreateRequestDto;
import com.kakao.cafe.web.dto.UserLoginRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/create")
    public String signUp(String userId, String password, String name, String email) {
        userService.signUp(new UserCreateRequestDto(userId, password, name, email));
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "user/list";
    }

    @GetMapping("/user/{userId}")
    public String getUserProfile(@PathVariable String userId, Model model) {
        System.out.println("22");
        model.addAttribute("user", userService.getUserProfile(userId));
        return "user/profile";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        if (session.getAttribute("user") == null && userService.login(new UserLoginRequestDto(userId, password))) {
            session.setAttribute("user", userId);
            return "redirect:/";
        }
        return "redirect:user/login_failed";
    }
}
