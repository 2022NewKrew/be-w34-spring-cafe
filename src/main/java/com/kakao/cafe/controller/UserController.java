package com.kakao.cafe.controller;

import com.kakao.cafe.model.domain.User;
import com.kakao.cafe.model.dto.UserDTO;
import com.kakao.cafe.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String goRegisterView() {
        return "user/register";
    }

    @PostMapping("/register")
    public String userRegister(UserDTO userDTO) {
        userService.registerUser(userDTO);
        return "redirect:/user/list";
    }

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user/list";
    }

    @GetMapping("/view/{userId}")
    public String userView(@PathVariable("userId") String userId, Model model) {
        model.addAttribute("user", userService.findUserByUserId(userId));
        return "user/view";
    }
}
