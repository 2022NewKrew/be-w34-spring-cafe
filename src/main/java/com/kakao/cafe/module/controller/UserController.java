package com.kakao.cafe.module.controller;

import com.kakao.cafe.module.model.dto.UserDto;
import com.kakao.cafe.module.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public String signUp(UserDto userDto){
        userService.signUp(userDto);
        return "redirect:/users/list";
    }

    @GetMapping("/list")
    public String userList(Model model){
        List<UserDto> userList = userService.userList();
        model.addAttribute("userList", userList);
        return "user/list";
    }

    @GetMapping("/{sn}")
    public String profile(@PathVariable Long sn, Model model){
        UserDto user = userService.findUser(sn);
        model.addAttribute("user", user);
        return "user/profile";
    }
}
