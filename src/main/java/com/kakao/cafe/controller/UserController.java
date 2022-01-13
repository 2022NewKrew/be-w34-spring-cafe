package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserRegisterRequest;
import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserList(Model model) {
        model.addAttribute("userList", userService.getUserList());
        return "users/list";
    }

    @PostMapping
    public String register(@Valid UserRegisterRequest requestDto) {
        userService.register(requestDto);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUserByUserId(@PathVariable("id") String userId, Model model) {
        model.addAttribute("user",
                userService.findByUserId(userId)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND)));
        return "users/profile";
    }
}
