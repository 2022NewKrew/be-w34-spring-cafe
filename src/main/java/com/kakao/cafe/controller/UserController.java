package com.kakao.cafe.controller;

import com.kakao.cafe.service.UserService;
import com.kakao.cafe.vo.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUserList(Model model){
        model.addAttribute("users", userService.findUsers());
        return "user/list";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user){
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/form")
    public String getForm(){
        return "user/form";
    }

    @GetMapping("/{id}")
    public String getId(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "user/profile";
    }


}
